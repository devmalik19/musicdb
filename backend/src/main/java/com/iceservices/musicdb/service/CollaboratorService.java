package com.iceservices.musicdb.service;

import com.iceservices.musicdb.data.dao.Artist;
import com.iceservices.musicdb.data.dao.Collaborator;
import com.iceservices.musicdb.data.dao.CollaboratorRole;
import com.iceservices.musicdb.data.dao.Track;
import com.iceservices.musicdb.data.exception.InvalidDataException;
import com.iceservices.musicdb.data.exception.InvalidRoleException;
import com.iceservices.musicdb.data.exception.ResourceNotFoundException;
import com.iceservices.musicdb.repository.CollaboratorRepository;
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
        Artist artist = artistService.getById(artistId);
        Track track = trackService.getById(trackId);

        if(artist==null)
            throw new ResourceNotFoundException("No such Artist !");

        if(track==null)
            throw new ResourceNotFoundException("No such track !");

        Collaborator collaborator = new Collaborator();
        try
        {
            collaborator.setRole(CollaboratorRole.valueOf(role.toUpperCase()));
        }
        catch (IllegalArgumentException e)
        {
            throw new InvalidRoleException("Invalid collaborator role !");
        }

        collaborator.setArtist(artist);
        collaborator.setTrack(track);
        Optional<Collaborator> found = collaboratorRepository.findOneByArtistIdAndTrackId(artistId, trackId);
        if(found.isPresent())
            throw new InvalidDataException("Already exists !");
        else
            collaboratorRepository.save(collaborator);
    }

    @SneakyThrows
    public void removeArtistFromTrack(Long trackId, Long artistId)
    {
        Artist artist = artistService.getById(artistId);
        Track track = trackService.getById(trackId);

        if(artist==null)
            throw new ResourceNotFoundException("No such Artist !");

        if(track==null)
            throw new ResourceNotFoundException("No such track !");


        Optional<Collaborator> found = collaboratorRepository.findOneByArtistIdAndTrackId(artistId, trackId);
        if(found.isPresent())
            collaboratorRepository.delete(found.get());
        else
            throw new InvalidDataException("Already deleted !");
    }
}
