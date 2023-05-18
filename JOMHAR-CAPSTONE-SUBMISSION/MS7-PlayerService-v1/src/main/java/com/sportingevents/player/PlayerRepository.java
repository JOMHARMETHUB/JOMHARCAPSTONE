package com.sportingevents.player;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {
    Page<PlayerEntity> findByActiveTrue(Pageable pageable);

    Optional<PlayerEntity> findByPlayerIdAndActiveTrue(Integer playerId);
}
