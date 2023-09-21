package com.iceservices.musicdb.controller;

import com.iceservices.musicdb.data.dao.Alias;
import com.iceservices.musicdb.data.dto.ApiResponseContainer;
import com.iceservices.musicdb.data.dto.ArtistResponse;
import com.iceservices.musicdb.service.AliasService;
import com.iceservices.musicdb.service.ApiResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("alias")
public class AliasController extends ApiControllerBase
{
    @Autowired
    private AliasService aliasService;

    @GetMapping("/{artistId}")
    public ResponseEntity<ApiResponseContainer> getAliases(@PathVariable Long id)
    {
        List<Alias> aliases = aliasService.getAliases(id);
        return apiResponseService.prepareApiResponse(aliases);
    }

    @PatchMapping("/{artistId}/add")
    public ResponseEntity<ApiResponseContainer> add(@PathVariable Long artistId,
                                                    @RequestParam String name)
    {
        aliasService.addAliasToArtist(artistId, name);
        return apiResponseService.prepareApiResponse("Alias added to artist", ApiResponseService.TYPE.CREATED);
    }

    @PatchMapping("/remove/{id}")
    public ResponseEntity<ApiResponseContainer> remove(@PathVariable Long id)
    {
        aliasService.removeAliasFromArtist(id);
        return apiResponseService.prepareApiResponse("Alias removed from the artist", ApiResponseService.TYPE.REMOVED);
    }
}
