package com.iceservices.musicdb.controller;

import com.iceservices.musicdb.data.dto.ApiResponseContainer;
import com.iceservices.musicdb.service.ApiResponseService;
import com.iceservices.musicdb.service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("collab")
public class CollaboratorController extends ApiControllerBase
{
    @Autowired
    private CollaboratorService collaboratorService;

    @PatchMapping("/{trackId}/add/{artistId}")
    public ResponseEntity<ApiResponseContainer> add(@PathVariable Long trackId,
                                                    @PathVariable Long artistId,
                                                    @RequestParam String role)
    {
        collaboratorService.addArtistToTrack(trackId, artistId, role);
        return apiResponseService.prepareApiResponse("Artist added to track", ApiResponseService.TYPE.CREATED);
    }

    @PatchMapping("/{trackId}/remove/{artistId}")
    public ResponseEntity<ApiResponseContainer> remove(@PathVariable Long trackId,
                                                    @PathVariable Long artistId,
                                                    @RequestParam String role)
    {
        collaboratorService.removeArtistFromTrack(trackId, artistId, role);
        return apiResponseService.prepareApiResponse("Artist removed from the track", ApiResponseService.TYPE.REMOVED);
    }
}
