package com.iceservices.musicdb.service;

import com.google.gson.JsonObject;
import com.iceservices.musicdb.data.dao.*;
import com.iceservices.musicdb.helper.CommonUtilities;
import com.iceservices.musicdb.repository.ArtistQueueRepository;
import com.iceservices.musicdb.repository.ArtistRepository;
import com.iceservices.musicdb.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistService
{
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ArtistQueueRepository artistQueueRepository;
    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CommonUtilities commonUtilities;

    public List<Artist> getList(String search, Pageable paging)
    {
        Page<Artist> page;
        if(search.isBlank())
            page = artistRepository.findAll(paging);
        else
            page = artistRepository.findByName(search, paging);
        return page.getContent();
    }

    public Artist getById(Long id)
    {
        Optional<Artist> Artist = artistRepository.findById(id);
        return Artist.get();
    }

    public void save(Artist artist)
    {
        Artist firstArtist = artistRepository.findFirstByOrderByIdDesc();
        Artist lastArtist = artistRepository.findFirstByOrderByIdDesc();

        artistRepository.save(artist);

        ArtistQueue artistQueue = new ArtistQueue();
        artistQueue.setArtist(artist);
        artistQueue.setNextArtist(firstArtist);
        artistQueueRepository.save(artistQueue);

        ArtistQueue artistQueuePrevious = artistQueueRepository.findByArtistId(lastArtist.getId()).get();
        artistQueuePrevious.setNextArtist(artist);
        artistQueueRepository.save(artistQueuePrevious);
    }

    public void update(Long id, Artist updateArtist)
    {
        Artist Artist = this.getById(id);
        if(updateArtist.getName()!=null)
            Artist.setName(updateArtist.getName());
        if(updateArtist.getBiography()!=null)
            Artist.setBiography(updateArtist.getBiography());
        if(updateArtist.getDob()!=null)
            Artist.setDob(updateArtist.getDob());
        if(updateArtist.getType()!=null)
            Artist.setType(updateArtist.getType());
        artistRepository.save(Artist);
    }

    public Artist getAotd()
    {
        Artist artist = null;
        Optional<State> optionalState = stateRepository.findByName("Artist.Of.The.Day");
        if(optionalState.isPresent())
        {
            State state = optionalState.get();
            JsonObject aotd = commonUtilities.convertStringToJsonObject(state.getData());
            LocalDate today =   commonUtilities.convertStringToLocalDate(aotd.get("date").toString());
            Long artistId = aotd.get("artistId").getAsLong();
            Optional<Artist> artistOptional = artistRepository.findById(artistId);
            if(artistOptional.isPresent())
            {
                if (today.equals(LocalDate.now()))
                {
                    artist = artistOptional.get();
                }
                else
                {
                    JsonObject jsonObject = new JsonObject();
                    Optional<ArtistQueue> artistQueue = artistQueueRepository.findByArtistId(artistId);
                    if(artistQueue.isPresent())
                    {
                        jsonObject.addProperty("date", LocalDate.now().toString());
                        jsonObject.addProperty("artistId", artistQueue.get().getNextArtist().getId());
                    }

                    state.setData(jsonObject.toString());
                    stateRepository.save(state);
                }
            }
        }
        return artist;
    }

    public List<Track> getTracks(Long id)
    {
        List<Track> trackList = new ArrayList<>();
        Optional<Artist> artist = artistRepository.findById(id);
        if(artist.isPresent())
        {
            trackList = artist.get().getCollaborators().stream().map(Collaborator::getTrack).collect(Collectors.toList());
        }

        return trackList;
    }
}
