package com.iceservices.musicdb.data.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Artist extends CommonDBFields
{
    private String name;
    private String biography;
    private LocalDate dob;
    private ArtistType type;

    @JsonManagedReference
    @OneToMany(mappedBy = "artist")
    private List<Collaborator> collaborators;

    @JsonManagedReference
    @OneToMany(mappedBy = "artist")
    private List<Alias> aliases;

}