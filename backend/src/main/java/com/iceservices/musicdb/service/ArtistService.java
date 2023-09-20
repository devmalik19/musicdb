package com.iceservices.musicdb.service;

import com.iceservices.musicdb.data.dao.*;
import com.iceservices.musicdb.data.dto.ArtistRequest;
import com.iceservices.musicdb.data.dto.ArtistResponse;
import com.iceservices.musicdb.data.dto.TrackResponse;
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

    public List<ArtistResponse> getList(String search, Pageable paging)
    {
        Page<Artist> page;
        if(search.isBlank())
            page = artistRepository.findAll(paging);
        else
            page = artistRepository.findByName(search, paging);
        return commonUtilities.convertArtistToArtistResponseList(page.getContent());
    }

    @SneakyThrows
    public ArtistResponse getById(Long id)
    {
        Optional<Artist> artist = artistRepository.findById(id);
        if(artist.isPresent())
            return commonUtilities.convertArtistToArtistResponseObject(artist.get());
        else
            throw new ResourceNotFoundException("No such artist!");
    }

    public Artist save(ArtistRequest artistRequest)
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

    @SneakyThrows
    public Artist update(Long id, ArtistRequest artistRequest)
    {
        Optional<Artist> artistOptional = artistRepository.findById(id);
        if(artistOptional.isPresent())
        {
            Artist artist = artistOptional.get();
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
        else
            throw new ResourceNotFoundException("No such artist !");

    }

    @SneakyThrows
    public ArtistResponse getAotd()
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
        return commonUtilities.convertArtistToArtistResponseObject(artist);
    }

    @SneakyThrows
    public List<TrackResponse> getTracks(Long id)
    {
        List<TrackResponse> trackList = new ArrayList<>();
        Optional<Artist> artist = artistRepository.findById(id);
        if(artist.isPresent())
        {
            artist.get().getCollaborators().stream().forEach(collaborator -> {
                TrackResponse trackResponse = new TrackResponse();
                Track track = collaborator.getTrack();
                trackResponse.setId(track.getId());
                trackResponse.setTitle(track.getTitle());
                trackResponse.setAlbum(track.getAlbum());
                trackResponse.setLength(track.getLength());
                trackResponse.setLanguage(track.getLanguage());
                trackResponse.setRelease(track.getRelease().toString());
                trackResponse.setGenre(track.getGenre());
                trackResponse.setArtistList(commonUtilities.getArtistList(track));
                trackList.add(trackResponse);
            });
        }
        else
            throw new ResourceNotFoundException("No such artist !");

        return trackList;
    }
}
