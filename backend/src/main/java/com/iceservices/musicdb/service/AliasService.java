package com.iceservices.musicdb.service;

import com.iceservices.musicdb.data.dao.Alias;
import com.iceservices.musicdb.data.dao.Artist;
import com.iceservices.musicdb.data.dto.ArtistResponse;
import com.iceservices.musicdb.data.exception.ResourceNotFoundException;
import com.iceservices.musicdb.repository.AliasRepository;
import com.iceservices.musicdb.repository.ArtistRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AliasService
{
    @Autowired
    private AliasRepository aliasRepository;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ArtistRepository artistRepository;

    public List<Alias> getAliases(Long artistId)
    {
        // To check if artist exits
        artistService.getById(artistId);

        Artist artist = artistRepository.findById(artistId).get();
        return artist.getAliases();
    }

    @SneakyThrows
    public void addAliasToArtist(Long artistId, String name)
    {
        // To check if artist exits
        ArtistResponse artist = artistService.getById(artistId);

        Alias alias = new Alias();
        alias.setArtist(artistRepository.findById(artistId).get());
        alias.setName(name);
        aliasRepository.save(alias);
    }

    public void removeAliasFromArtist(Long id)
    {
        aliasRepository.deleteById(id);
    }
}
