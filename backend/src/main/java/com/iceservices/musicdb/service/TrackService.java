package com.iceservices.musicdb.service;

import com.iceservices.musicdb.data.dao.Track;
import com.iceservices.musicdb.repository.TrackRepository;
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
            page = trackRepository.findByTitle(search, paging);
        return page.getContent();
    }

    public Track getById(Long id)
    {
        Optional<Track> track = trackRepository.findById(id);
        return track.get();
    }

    public void save(Track track)
    {
        trackRepository.save(track);
    }

    public void update(Long id, Track updateTrack)
    {
        Track track = this.getById(id);
        if(updateTrack.getTitle()!=null)
            track.setTitle(updateTrack.getTitle());
        if(updateTrack.getAlbum()!=null)
            track.setAlbum(updateTrack.getAlbum());
        if(updateTrack.getLength()!=null)
            track.setLength(updateTrack.getLength());
        if(updateTrack.getGenre()!=null)
            track.setGenre(updateTrack.getGenre());
        if(updateTrack.getRelease()!=null)
            track.setRelease(updateTrack.getRelease());
        if(updateTrack.getLanguage()!=null)
            track.setLanguage(updateTrack.getLanguage());
        trackRepository.save(track);
    }
}

