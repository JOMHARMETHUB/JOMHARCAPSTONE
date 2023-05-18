package com.sportingevents.ticket;

import com.sportingevents.common.constant.ApiEndpoint;
import com.sportingevents.match.MatchRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TICKETS)
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private MatchRestService matchRestService;

    @GetMapping
    public ResponseEntity<List<TicketResponseModel>> getAllTickets(Pageable pageable) {
        return ResponseEntity.ok(ticketService.getAllTickets(pageable));
    }

    @GetMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<TicketResponseModel> getTicket(@PathVariable("id") Integer ticketId) {
        return ResponseEntity.ok(ticketService.getTicket(ticketId));
    }

    @PostMapping
    public ResponseEntity<String> saveTicket(@RequestBody @Validated TicketRequestModel ticketRequestModel, @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken) {
        matchRestService.getMatch(ticketRequestModel.getMatchId(), jwtToken);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.saveTicket(ticketRequestModel));
    }

    @PutMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<String> updateTicket(@PathVariable("id") Integer ticketId, @RequestBody @Validated TicketRequestModel ticketRequestModel, @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken) {
        matchRestService.getMatch(ticketRequestModel.getMatchId(), jwtToken);
        return ResponseEntity.ok(ticketService.updateTicket(ticketId, ticketRequestModel));
    }

    @DeleteMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<String> deleteTicket(@PathVariable("id") Integer ticketId) {
        return ResponseEntity.ok(ticketService.deleteTicket(ticketId));
    }
}
