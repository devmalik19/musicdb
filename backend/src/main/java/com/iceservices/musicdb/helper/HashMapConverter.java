package com.iceservices.musicdb.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class HashMapConverter implements AttributeConverter<Map<String, Object>, String>
{
    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Map<String, Object> state)
    {
        return new ObjectMapper().writeValueAsString(state);
    }

    @SneakyThrows
    @Override
    public Map<String, Object> convertToEntityAttribute(String state)
    {
        return new ObjectMapper().readValue(state, new TypeReference<HashMap<String, Object>>() {});
    }
}