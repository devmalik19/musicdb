package com.iceservices.musicdb.data.dao;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ArtistQueue
{
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    private Artist artist;

    @OneToOne
    @JoinColumn(name = "next_artist", referencedColumnName = "id")
    private Artist nextArtist;

}
