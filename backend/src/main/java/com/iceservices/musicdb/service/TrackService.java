package com.iceservices.musicdb.service;

import com.iceservices.musicdb.data.dao.Track;
import com.iceservices.musicdb.data.dto.TrackRequest;
import com.iceservices.musicdb.data.exception.ResourceNotFoundException;
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

    public List<Track> getList(String search, Pageable paging)
    {
        Page<Track> page;
        if(search.isBlank())
            page = trackRepository.findAll(paging);
        else
            page = trackRepository.findByTitleContainingIgnoreCase(search, paging);
        return page.getContent();
    }

    @SneakyThrows
    public Track getById(Long id)
    {
        Optional<Track> track = trackRepository.findById(id);
        if(track.isPresent())
            return track.get();
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

    public Track update(Long id, TrackRequest trackRequest)
    {
        Track track = this.getById(id);
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
}

