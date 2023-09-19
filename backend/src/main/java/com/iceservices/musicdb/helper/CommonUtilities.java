package com.iceservices.musicdb.helper;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CommonUtilities
{
    public JsonObject convertStringToJsonObject(String string)
    {
        return JsonParser.parseString(string).getAsJsonObject();
    }

    public LocalDate convertStringToLocalDate(String string)
    {
        return LocalDate.parse(string);
    }
}
