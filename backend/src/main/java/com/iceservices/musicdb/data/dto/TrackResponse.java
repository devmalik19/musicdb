package com.iceservices.musicdb.data.dto;

import com.iceservices.musicdb.data.constant.Genre;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrackResponse
{
    private Long id;

    private String title;
    private String album;

    private Genre genre;

    private Integer length;
    private String release;
    private String language;

    private List<ArtistResponse> artistList;
}
