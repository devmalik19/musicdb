package com.iceservices.musicdb.repository;

import com.iceservices.musicdb.data.dao.ArtistQueue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistQueueRepository extends JpaRepository<ArtistQueue, Long>
{
    Optional<ArtistQueue> findByArtistId(Long id);
}
