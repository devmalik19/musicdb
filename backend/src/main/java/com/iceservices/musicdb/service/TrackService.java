package com.iceservices.musicdb.service;

import com.iceservices.musicdb.data.dao.Track;
import com.iceservices.musicdb.data.dto.TrackRequest;
import com.iceservices.musicdb.data.dto.TrackResponse;
import com.iceservices.musicdb.data.exception.ResourceNotFoundException;
import com.iceservices.musicdb.helper.CommonUtilities;
import com.iceservices.musicdb.repository.TrackRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackService
{
    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private CommonUtilities commonUtilities;

    public List<TrackResponse> getList(String search, Pageable paging)
    {
        Page<Track> page;
        if(search.isBlank())
            page = trackRepository.findAll(paging);
        else
            page = trackRepository.findByTitleContainingIgnoreCase(search, paging);
        return commonUtilities.convertTrackListToTrackResponseList(page.getContent());
    }

    @SneakyThrows
    public TrackResponse getById(Long id)
    {
        Optional<Track> track = trackRepository.findById(id);
        if(track.isPresent())
        {
            return commonUtilities.convertTrackToTrackResponseObject(track.get());
        }
        else
            throw new ResourceNotFoundException("No Artist found!");
    }

    public Track save(TrackRequest trackRequest)
    {
        Track track = new Track();
        track.setTitle(trackRequest.getTitle());
        track.setAlbum(trackRequest.getAlbum());
        track.setGenre(trackRequest.getGenre());
        track.setLength(trackRequest.getLength());
        track.setRelease(trackRequest.getRelease());
        return trackRepository.save(track);
    }

    @SneakyThrows
    public Track update(Long id, TrackRequest trackRequest)
    {
        Optional<Track> trackOptional = trackRepository.findById(id);
        if(trackOptional.isPresent())
        {
            Track track = trackOptional.get();
            if(trackRequest.getTitle()!=null)
                track.setTitle(trackRequest.getTitle());
            if(trackRequest.getAlbum()!=null)
                track.setAlbum(trackRequest.getAlbum());
            if(trackRequest.getLength()!=null)
                track.setLength(trackRequest.getLength());
            if(trackRequest.getGenre()!=null)
                track.setGenre(trackRequest.getGenre());
            if(trackRequest.getRelease()!=null)
                track.setRelease(trackRequest.getRelease());
            if(trackRequest.getLanguage()!=null)
                track.setLanguage(trackRequest.getLanguage());
            return trackRepository.save(track);
        }
        else
            throw new ResourceNotFoundException("No Artist found!");

    }
}

