package com.iceservices.musicdb.data.dao;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Alias extends CommonDBFields
{
    private String name;

    @OneToOne
    private Artist artist;
}
