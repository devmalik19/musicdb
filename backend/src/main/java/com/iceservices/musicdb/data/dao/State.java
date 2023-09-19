package com.iceservices.musicdb.data.dao;

import com.iceservices.musicdb.helper.HashMapConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Entity
@Getter
@Setter
public class State extends CommonDBFields
{
    private String name;

    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> data;
}
