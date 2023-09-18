package com.iceservices.musicdb.repository;

import com.iceservices.musicdb.data.dao.Alias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AliasRepository extends JpaRepository<Alias, Long>
{
    Optional<Alias> findByNameAndArtistId(String name, Long artistId);
}
