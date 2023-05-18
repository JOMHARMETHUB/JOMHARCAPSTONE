package com.sportingevents.ticket;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketService {
    List<TicketResponseModel> getAllTickets(Pageable pageable);

    TicketResponseModel getTicket(Integer ticketId);

    String saveTicket(TicketRequestModel ticketRequestModel);

    String updateTicket(Integer ticketId, TicketRequestModel ticketRequestModel);

    String deleteTicket(Integer ticketId);
}
