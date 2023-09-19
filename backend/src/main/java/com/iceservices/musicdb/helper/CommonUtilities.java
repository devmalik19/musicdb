package com.iceservices.musicdb.helper;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CommonUtilities
{
    public LocalDate convertStringToLocalDate(String string)
    {
        return LocalDate.parse(string);
    }
}
