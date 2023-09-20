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
public class ArtistControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getList() throws Exception
    {
        this.mockMvc.perform(get("/artist")).andExpect(status().isOk());
    }

    @Test
    public void getById() throws Exception
    {
        this.mockMvc.perform(get("/artist/1")).andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception
    {
        this.mockMvc.perform(post("/artist").content("{\n" +
                "    \"name\" : \"Zazzy B\",\n" +
                "    \"biography\" : \"New kid on the block\",\n" +
                "    \"dob\" : \"1989-02-02\",\n" +
                "    \"type\" : \"INDIVIDUAL\"\n" +
                "}  ").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception
    {
        this.mockMvc.perform(patch("/artist/1").content("{\n" +
                "    \"name\" : \"Zazzy A\",\n" +
                "    \"biography\" : \"Old kid on the block\",\n" +
                "    \"dob\" : \"1989-03-02\",\n" +
                "    \"type\" : \"INDIVIDUAL\"\n" +
                "}   ").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getArtistOfTheDay() throws Exception
    {
        this.mockMvc.perform(get("/artist/aotd")).andExpect(status().isOk());
    }

    @Test
    public void getTracks() throws Exception
    {
        this.mockMvc.perform(get("/artist/1/tracks")).andExpect(status().isOk());
    }
}
