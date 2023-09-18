package com.iceservices.musicdb.service;

import com.iceservices.musicdb.data.dao.Artist;
import com.iceservices.musicdb.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService
{
    @Autowired
    private ArtistRepository artistRepository;

    public List<Artist> getList(String search, Pageable paging)
    {
        Page<Artist> page;
        if(search.isBlank())
            page = artistRepository.findAll(paging);
        else
            page = artistRepository.findByName(search, paging);
        return page.getContent();
    }

    public Artist getById(Long id)
    {
        Optional<Artist> Artist = artistRepository.findById(id);
        return Artist.get();
    }

    public void save(Artist Artist)
    {
        artistRepository.save(Artist);
    }

    public void update(Long id, Artist updateArtist)
    {
        Artist Artist = this.getById(id);
        if(updateArtist.getName()!=null)
            Artist.setName(updateArtist.getName());
        if(updateArtist.getBiography()!=null)
            Artist.setBiography(updateArtist.getBiography());
        if(updateArtist.getDob()!=null)
            Artist.setDob(updateArtist.getDob());
        if(updateArtist.getType()!=null)
            Artist.setType(updateArtist.getType());
        artistRepository.save(Artist);
    }
}
