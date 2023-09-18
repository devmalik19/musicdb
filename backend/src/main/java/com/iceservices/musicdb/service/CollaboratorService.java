package com.iceservices.musicdb.service;

import com.iceservices.musicdb.data.dao.Collaborator;
import com.iceservices.musicdb.repository.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollaboratorService
{
    @Autowired
    private CollaboratorRepository collaboratorRepository;

    public List<Collaborator> findAll()
    {
        return collaboratorRepository.findAll();
    }
}
