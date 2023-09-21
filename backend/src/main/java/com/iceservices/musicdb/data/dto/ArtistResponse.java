package com.iceservices.musicdb.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistResponse
{
    private Long id;
    private String name;
    private String biography;
    private String  dob;

    private String type;
    private String role;

    private String[] aliases;
}
