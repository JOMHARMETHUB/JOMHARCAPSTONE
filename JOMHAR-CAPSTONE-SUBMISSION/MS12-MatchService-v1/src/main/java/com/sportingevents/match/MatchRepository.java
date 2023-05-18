package com.sportingevents.match;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Integer> {
    Page<MatchEntity> findByActiveTrue(Pageable pageable);

    Optional<MatchEntity> findByMatchIdAndActiveTrue(Integer matchId);
}
