package com.iceservices.musicdb.repository;

import com.iceservices.musicdb.data.dao.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StateRepository extends JpaRepository<State, Long>
{
    Optional<State> findByKey(String key);
}
