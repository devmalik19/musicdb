package com.iceservices.musicdb.service;

import com.iceservices.musicdb.data.dao.Artist;
import com.iceservices.musicdb.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService
{
    @Autowired
    private ArtistRepository artistRepository;

    public List<Artist> findAll()
    {
        return artistRepository.findAll();
    }
}
