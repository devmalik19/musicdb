package com.iceservices.musicdb.data.dao;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class State extends CommonDBFields
{
    private String name;
    private String data;
}
