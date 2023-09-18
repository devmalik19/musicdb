package com.iceservices.musicdb.repository;

import com.iceservices.musicdb.data.dao.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long>
{
    Optional<Collaborator> findOneByArtistIdAndTrackId(Long artistId, Long trackId);
}
