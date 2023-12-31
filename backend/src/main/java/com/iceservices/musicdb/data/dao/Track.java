package com.iceservices.musicdb.data.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iceservices.musicdb.data.constant.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Track extends CommonDBFields
{

    private String title;
    private String album;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private Integer length;
    private LocalDate release;
    private String language;

    @JsonManagedReference
    @OneToMany(mappedBy = "track")
    private List<Collaborator> collaborator;
}
