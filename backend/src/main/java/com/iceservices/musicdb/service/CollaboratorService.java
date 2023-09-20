package com.iceservices.musicdb.service;

import com.iceservices.musicdb.data.dao.Artist;
import com.iceservices.musicdb.data.dao.Collaborator;
import com.iceservices.musicdb.data.constant.CollaboratorRole;
import com.iceservices.musicdb.data.dao.Track;
import com.iceservices.musicdb.data.dto.ArtistResponse;
import com.iceservices.musicdb.data.dto.TrackResponse;
import com.iceservices.musicdb.data.exception.InvalidDataException;
import com.iceservices.musicdb.data.exception.InvalidRoleException;
import com.iceservices.musicdb.data.exception.ResourceNotFoundException;
import com.iceservices.musicdb.repository.ArtistRepository;
import com.iceservices.musicdb.repository.CollaboratorRepository;
import com.iceservices.musicdb.repository.TrackRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollaboratorService
{
    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private TrackService trackService;

    public List<Collaborator> findAll()
    {
        return collaboratorRepository.findAll();
    }

    @SneakyThrows
    public void addArtistToTrack(Long trackId, Long artistId, String role)
    {
        // To check if artist and track exits
        ArtistResponse artist = artistService.getById(artistId);
        TrackResponse track = trackService.getById(trackId);

        Collaborator collaborator = new Collaborator();
        try
        {
            collaborator.setRole(CollaboratorRole.valueOf(role.toUpperCase()));
        }
        catch (IllegalArgumentException e)
        {
            throw new InvalidRoleException("Invalid collaborator role !");
        }

        collaborator.setArtist(artistRepository.findById(artistId).get());
        collaborator.setTrack(trackRepository.findById(trackId).get());
        Optional<Collaborator> found = collaboratorRepository.findOneByArtistIdAndTrackId(artistId, trackId);
        if(found.isPresent())
            throw new InvalidDataException("Already exists !");
        else
            collaboratorRepository.save(collaborator);
    }

    @SneakyThrows
    public void removeArtistFromTrack(Long trackId, Long artistId)
    {
        // To check if artist and track exits
        ArtistResponse artist = artistService.getById(artistId);
        TrackResponse track = trackService.getById(trackId);

        Optional<Collaborator> found = collaboratorRepository.findOneByArtistIdAndTrackId(artistId, trackId);
        if(found.isPresent())
            collaboratorRepository.delete(found.get());
        else
            throw new InvalidDataException("Already deleted !");
    }
}
