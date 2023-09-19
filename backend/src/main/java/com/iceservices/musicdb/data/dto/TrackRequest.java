package com.iceservices.musicdb.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TrackRequest
{
    private String title;
    private String album;
    private String genre;
    private Integer length;
    private LocalDate release;
    private String language;
}
