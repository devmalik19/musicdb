package com.iceservices.musicdb.repository;

import com.iceservices.musicdb.data.dao.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>
{
}
