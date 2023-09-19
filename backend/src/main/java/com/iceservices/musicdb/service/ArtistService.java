package com.iceservices.musicdb.service;

import com.iceservices.musicdb.data.dao.*;
import com.iceservices.musicdb.data.dto.ArtistRequest;
import com.iceservices.musicdb.data.exception.ResourceNotFoundException;
import com.iceservices.musicdb.helper.CommonUtilities;
import com.iceservices.musicdb.repository.ArtistQueueRepository;
import com.iceservices.musicdb.repository.ArtistRepository;
import com.iceservices.musicdb.repository.StateRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public Artist save( ArtistRequest artistRequest)
    {
        Artist firstArtist = artistRepository.findFirstByOrderByIdDesc();
        Artist lastArtist = artistRepository.findFirstByOrderByIdDesc();

        Artist artist =  new Artist();
        artist.setName(artistRequest.getName());
        artist.setType(artistRequest.getType());
        artist.setDob(artistRequest.getDob());
        artist.setBiography(artistRequest.getBiography());
        artistRepository.save(artist);

        ArtistQueue artistQueue = new ArtistQueue();
        artistQueue.setArtist(artist);
        artistQueue.setNextArtist(firstArtist);
        artistQueueRepository.save(artistQueue);

        ArtistQueue artistQueuePrevious = artistQueueRepository.findByArtistId(lastArtist.getId()).get();
        artistQueuePrevious.setNextArtist(artist);
        artistQueueRepository.save(artistQueuePrevious);

        return artist;
    }

    public Artist update(Long id, ArtistRequest artistRequest)
    {
        Artist artist = this.getById(id);
        if(artistRequest.getName()!=null)
            artist.setName(artistRequest.getName());
        if(artistRequest.getBiography()!=null)
            artist.setBiography(artistRequest.getBiography());
        if(artistRequest.getDob()!=null)
            artist.setDob(artistRequest.getDob());
        if(artistRequest.getType()!=null)
            artist.setType(artistRequest.getType());
        return artistRepository.save(artist);
    }

    @SneakyThrows
    public Artist getAotd()
    {
        Artist artist = null;
        Optional<State> optionalState = stateRepository.findByName("Artist.Of.The.Day");
        if(optionalState.isPresent())
        {
            State state = optionalState.get();
            Map<String, Object> data = state.getData();
            LocalDate today =   commonUtilities.convertStringToLocalDate(data.get("date").toString());
            Long artistId =  Long.parseLong(data.get("artistId").toString());
            Optional<Artist> artistOptional = artistRepository.findById(artistId);
            if(artistOptional.isPresent())
            {
                if (today.equals(LocalDate.now()))
                {
                    artist = artistOptional.get();
                }
                else
                {
                    Optional<ArtistQueue> artistQueue = artistQueueRepository.findByArtistId(artistId);
                    if(artistQueue.isPresent())
                    {
                        data.put("date", LocalDate.now().toString());
                        data.put("artistId", artistQueue.get().getNextArtist().getId());
                    }

                    state.setData(data);
                    stateRepository.save(state);

                    artistOptional = artistRepository.findById((Long) data.get("artistId"));
                    if(artistOptional.isPresent())
                    {
                        artist = artistOptional.get();

                    }
                    else
                    {
                        throw new ResourceNotFoundException("No such artist !");
                    }
                }
            }
            else
            {
                throw new ResourceNotFoundException("No such artist !");
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
