package com.iceservices.musicdb.controller;

import com.iceservices.musicdb.data.dao.Collaborator;
import com.iceservices.musicdb.service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("collaborator")
public class CollaboratorController
{
    @Autowired
    private CollaboratorService collaboratorService;

    @GetMapping
    public List<Collaborator> findAll()
    {
        return collaboratorService.findAll();
    }
}
