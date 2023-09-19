package com.iceservices.musicdb.repository;

import com.iceservices.musicdb.data.dao.Track;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrackRepository extends JpaRepository<Track, Long>
{
    Page<Track> findByTitleContainingIgnoreCase(String search, Pageable paging);
}
