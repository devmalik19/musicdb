package com.iceservices.musicdb.data.dao;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    private Artist artist;
}
