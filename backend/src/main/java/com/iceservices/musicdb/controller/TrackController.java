package com.iceservices.musicdb.controller;

import com.iceservices.musicdb.data.dao.Track;
import com.iceservices.musicdb.data.dto.ApiResponseContainer;
import com.iceservices.musicdb.data.dto.TrackRequest;
import com.iceservices.musicdb.data.dto.TrackResponse;
import com.iceservices.musicdb.service.TrackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("track")
public class TrackController extends ApiControllerBase
{
    @Autowired
    private TrackService trackService;

    @GetMapping
    public ResponseEntity<ApiResponseContainer> getList(@RequestParam(defaultValue = "0", required = false) int page,
                                                        @RequestParam(defaultValue = "100", required = false) int size,
                                                        @RequestParam(defaultValue = "", required = false) String search)
    {
        Pageable paging = PageRequest.of(page, size);
        List<TrackResponse> trackList = trackService.getList(search, paging);
        return apiResponseService.prepareApiResponse(trackList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseContainer> getById(@PathVariable Long id)
    {
        TrackResponse track = trackService.getById(id);
        return apiResponseService.prepareApiResponse(track);
    }

    @PostMapping
    public ResponseEntity<ApiResponseContainer> create(@Valid @RequestBody TrackRequest trackRequest)
    {
        Track track = trackService.save(trackRequest);
        return apiResponseService.prepareApiResponse(track);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponseContainer> update(@PathVariable Long id, @Valid @RequestBody TrackRequest trackRequest)
    {
        Track track = trackService.update(id,trackRequest);
        return apiResponseService.prepareApiResponse(track);
    }

}
