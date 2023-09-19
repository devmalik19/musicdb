package com.iceservices.musicdb.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AliasControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void add() throws Exception
    {
        this.mockMvc.perform(patch("/alias/1/add?name=Apple")).andExpect(status().isCreated());
    }

    @Test
    public void remove() throws Exception
    {
        this.mockMvc.perform(patch("/alias/remove/1")).andExpect(status().isOk());
    }
}
