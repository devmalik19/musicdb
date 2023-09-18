package com.iceservices.musicdb.controller;

import com.iceservices.musicdb.data.dao.Artist;
import com.iceservices.musicdb.data.dto.ApiResponseContainer;
import com.iceservices.musicdb.service.ArtistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("artist")
public class ArtistController extends ApiControllerBase
{
    @Autowired
    private ArtistService artistService;

    @GetMapping
    public ResponseEntity<ApiResponseContainer> getList(@RequestParam(defaultValue = "0", required = false) int page,
                                                        @RequestParam(defaultValue = "2", required = false) int size,
                                                        @RequestParam(defaultValue = "", required = false) String search)
    {
        Pageable paging = PageRequest.of(page, size);
        List<Artist> trackList = artistService.getList(search, paging);
        return apiResponseService.prepareApiResponse(trackList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseContainer> getById(@PathVariable Long id)
    {
        Artist artist = artistService.getById(id);
        return apiResponseService.prepareApiResponse(artist);
    }

    @PostMapping
    public ResponseEntity<ApiResponseContainer> create(@Valid @RequestBody Artist artist)
    {
        artistService.save(artist);
        return apiResponseService.prepareApiResponse(artist);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponseContainer> update(@PathVariable Long id, @RequestBody Artist artist)
    {
        artistService.update(id,artist);
        return apiResponseService.prepareApiResponse(artist);
    }
}