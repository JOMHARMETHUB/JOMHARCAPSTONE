package com.sportingevents.field;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldRepository extends JpaRepository<FieldEntity, Integer> {
    Page<FieldEntity> findByActiveTrue(Pageable pageable);

    Optional<FieldEntity> findByFieldIdAndActiveTrue(Integer fieldId);
}
