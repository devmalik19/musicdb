package com.iceservices.musicdb.data.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Collaborator extends CommonDBFields
{
    @JsonBackReference
    @ManyToOne
    private Track track;

    @JsonBackReference
    @ManyToOne
    private Artist artist;

    @Enumerated(EnumType.STRING)
    private CollaboratorRole role;
}
