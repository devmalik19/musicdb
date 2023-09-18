package com.iceservices.musicdb.repository;

import com.iceservices.musicdb.data.dao.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long>
{
}
