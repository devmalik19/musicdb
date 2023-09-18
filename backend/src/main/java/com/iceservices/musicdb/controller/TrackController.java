package com.iceservices.musicdb.controller;

import com.iceservices.musicdb.data.dao.Track;
import com.iceservices.musicdb.data.dto.ApiResponseContainer;
import com.iceservices.musicdb.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("track")
public class TrackController extends ApiControllerBase
{
    @Autowired
    private TrackService trackService;

    @GetMapping
    public ResponseEntity<ApiResponseContainer> findAll()
    {
        List<Track> trackList = trackService.findAll();
        return apiResponseService.prepareApiResponse(trackList);
    }

    @GetMapping
    public ResponseEntity<ApiResponseContainer> findById()
    {
        List<Track> trackList = trackService.findAll();
        return apiResponseService.prepareApiResponse(trackList);
    }
}
