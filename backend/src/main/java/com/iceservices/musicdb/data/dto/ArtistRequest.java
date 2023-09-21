package com.iceservices.musicdb.data.dto;

import com.iceservices.musicdb.data.constant.ArtistType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ArtistRequest
{
    private String name;
    private String biography;
    private LocalDate dob;
    private ArtistType type;
    private String[] aliases;
}
