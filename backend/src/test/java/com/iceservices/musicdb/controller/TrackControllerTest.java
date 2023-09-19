package com.iceservices.musicdb.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TrackControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getList() throws Exception
    {
        this.mockMvc.perform(get("/track")).andExpect(status().isOk());
    }

    @Test
    public void getById() throws Exception
    {
        this.mockMvc.perform(get("/track/1")).andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception
    {
        this.mockMvc.perform(post("/track").content("{\n" +
                "    \"title\" : \"TRACK NEW\",\n" +
                "    \"album\" : \"album new\",\n" +
                "    \"genre\" : \"POP\",\n" +
                "    \"length\" : 200,\n" +
                "    \"release\" : \"2023-02-02\",\n" +
                "    \"language\" : \"English\"\n" +
                "}").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception
    {
        this.mockMvc.perform(patch("/track/1").content("{\n" +
                "    \"title\" : \"TRACK edit\",\n" +
                "    \"album\" : \"album old\",\n" +
                "    \"genre\" : \"HIPHOP\",\n" +
                "    \"length\" : 201,\n" +
                "    \"release\" : \"2023-02-03\",\n" +
                "    \"language\" : \"Hindi\"\n" +
                "}").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
