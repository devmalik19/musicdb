package com.iceservices.musicdb.controller;

import com.iceservices.musicdb.data.dao.Artist;
import com.iceservices.musicdb.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArtistController
{
    @Autowired
    private ArtistService artistService;

    @GetMapping
    public List<Artist> findAll()
    {
        return artistService.findAll();
    }
}
