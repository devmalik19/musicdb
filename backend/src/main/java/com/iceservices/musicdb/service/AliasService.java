package com.iceservices.musicdb.service;

import com.iceservices.musicdb.data.dao.Alias;
import com.iceservices.musicdb.data.dao.Artist;
import com.iceservices.musicdb.data.exception.ResourceNotFoundException;
import com.iceservices.musicdb.repository.AliasRepository;
import com.iceservices.musicdb.repository.ArtistRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AliasService
{
    @Autowired
    private AliasRepository aliasRepository;

    @Autowired
    private ArtistService artistService;

    @SneakyThrows
    public void addAliasToArtist(Long artistId, String name)
    {
        Artist artist = artistService.getById(artistId);
        if(artist==null)
            throw new ResourceNotFoundException("No such Artist !");

        Alias alias = new Alias();
        aliasRepository.save(alias);
    }

    public void removeAliasFromArtist(Long id)
    {
        aliasRepository.deleteById(id);
    }
}
