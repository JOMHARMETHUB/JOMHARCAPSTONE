package com.sportingevents.ticket;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
    Page<TicketEntity> findByActiveTrue(Pageable pageable);

    Optional<TicketEntity> findByTicketIdAndActiveTrue(Integer ticketId);
}
