package com.iceservices.musicdb.service;

import com.iceservices.musicdb.data.dao.Track;
import com.iceservices.musicdb.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService
{
    @Autowired
    private TrackRepository trackRepository;

    public List<Track> findAll()
    {
        return trackRepository.findAll();
    }
}

