package com.sportingevents.team;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {
    Page<TeamEntity> findByActiveTrue(Pageable pageable);

    Optional<TeamEntity> findByTeamNameAndActiveTrue(String teamName);

    Optional<TeamEntity> findByTeamIdAndActiveTrue(Integer teamId);

    Optional<TeamEntity> findByTeamName(String teamName);
}
