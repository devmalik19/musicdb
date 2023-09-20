package com.iceservices.musicdb.controller;

import com.iceservices.musicdb.data.dao.Artist;
import com.iceservices.musicdb.data.dao.Track;
import com.iceservices.musicdb.data.dto.ApiResponseContainer;
import com.iceservices.musicdb.data.dto.ArtistRequest;
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
                                                        @RequestParam(defaultValue = "100", required = false) int size,
                                                        @RequestParam(defaultValue = "", required = false) String search)
    {
        Pageable paging = PageRequest.of(page, size);
        List<Artist> artistList = artistService.getList(search, paging);
        return apiResponseService.prepareApiResponse(artistList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseContainer> getById(@PathVariable Long id)
    {
        Artist artist = artistService.getById(id);
        return apiResponseService.prepareApiResponse(artist);
    }

    @PostMapping
    public ResponseEntity<ApiResponseContainer> create(@Valid @RequestBody ArtistRequest artistRequest)
    {
        Artist artist = artistService.save(artistRequest);
        return apiResponseService.prepareApiResponse(artist);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponseContainer> update(@PathVariable Long id, @Valid @RequestBody ArtistRequest artistRequest)
    {
        Artist artist = artistService.update(id, artistRequest);
        return apiResponseService.prepareApiResponse(artist);
    }

    @GetMapping("/aotd")
    public ResponseEntity<ApiResponseContainer> getArtistOfTheDay()
    {
        Artist artist = artistService.getAotd();
        return apiResponseService.prepareApiResponse(artist);
    }

    @GetMapping("/{id}/tracks")
    public ResponseEntity<ApiResponseContainer> getTracks(@PathVariable Long id)
    {
        List<Track> trackList = artistService.getTracks(id);
        return apiResponseService.prepareApiResponse(trackList);
    }

}
