package com.sportingevents.ticket;

import com.sportingevents.match.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TicketServiceTest {

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenGetAllTickets_returnTickets() {
        Pageable page = PageRequest.of(0, 10);
        when(ticketRepository.findByActiveTrue(any(Pageable.class))).thenReturn(getPageTickets());
        List<TicketResponseModel> ticketResponseModels = ticketService.getAllTickets(page);
        Assert.assertNotNull(ticketResponseModels);
    }

    @Test
    public void whenGetAllTicketsEmpty_returnMessage() {
        exception.expect(TicketException.class);
        exception.expectMessage(TicketMessage.NO_TICKETS_FOUND);
        Pageable page = PageRequest.of(0, 10);
        when(ticketRepository.findByActiveTrue(any(Pageable.class))).thenReturn(Page.empty());
        ticketService.getAllTickets(page);
    }

    @Test
    public void whenGetTicket_returnTicket() {
        TicketEntity ticketEntity = getTicketEntity();
        when(ticketRepository.findByTicketIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(ticketEntity));
        TicketResponseModel result = ticketService.getTicket(1);
        Assert.assertNotNull(result);
    }

    @Test
    public void whenGetTicketNotExisting_returnMessage() {
        exception.expect(TicketException.class);
        exception.expectMessage(TicketMessage.NO_TICKETS_FOUND);
        when(ticketRepository.findByTicketIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.empty());
        ticketService.getTicket(1);
    }

    @Test
    public void givenValidRequestModel_whenSaveTicket_returnMessage() {
        TicketRequestModel ticketRequestModel = getTicketRequestModel();
        String result = ticketService.saveTicket(ticketRequestModel);
        Assert.assertEquals(TicketMessage.TICKET_SAVE_SUCCESS, result);
    }

    @Test
    public void givenInvalidCustomerName_whenSaveTicket_returnMessage() {
        exception.expect(TicketException.class);
        exception.expectMessage(TicketMessage.INVALID_NAME);
        TicketRequestModel ticketRequestModel = getTicketRequestModel();
        ticketRequestModel.setCustomerName("cust1");
        ticketService.saveTicket(ticketRequestModel);
    }

    @Test
    public void givenValidRequestModelAndId_whenUpdateTicket_returnMessage() {
        TicketRequestModel ticketRequestModel = getTicketRequestModel();
        when(ticketRepository.findByTicketIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(getTicketEntity()));
         String result = ticketService.updateTicket(1, ticketRequestModel);
        Assert.assertEquals(TicketMessage.TICKET_UPDATE_SUCCESS, result);
    }

    @Test
    public void givenTicketNotExisting_whenUpdateTicket_returnMessage() {
        exception.expect(TicketException.class);
        exception.expectMessage(TicketMessage.NO_TICKETS_FOUND);
        TicketRequestModel ticketRequestModel = getTicketRequestModel();
        when(ticketRepository.findByTicketIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.empty());
        ticketService.updateTicket(1, ticketRequestModel);
    }

    @Test
    public void givenInvalidName_whenUpdateTicket_returnMessage() {
        exception.expect(TicketException.class);
        exception.expectMessage(TicketMessage.INVALID_NAME);
        TicketRequestModel ticketRequestModel = getTicketRequestModel();
        ticketRequestModel.setCustomerName("cust1");
        when(ticketRepository.findByTicketIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(getTicketEntity()));
        ticketService.updateTicket(1, ticketRequestModel);
    }

    @Test
    public void givenValidId_whenDeleteTicket_returnMessage() {
        when(ticketRepository.findByTicketIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(getTicketEntity()));
        String result = ticketService.deleteTicket(1);
        Assert.assertEquals(TicketMessage.TICKET_DELETE_SUCCESS, result);
    }

    @Test
    public void givenNotExistingTicket_whenDeleteTicket_returnMessage() {
        exception.expect(TicketException.class);
        exception.expectMessage(TicketMessage.NO_TICKETS_FOUND);
        when(ticketRepository.findByTicketIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.empty());
        ticketService.deleteTicket(1);
    }


    private Page<TicketEntity> getPageTickets() {
        Pageable pageable = PageRequest.of(0, 10);
        List<TicketEntity> tickets = getTicketEntities();
        int start = Math.min((int)pageable.getOffset(), tickets.size());
        int end = Math.min((start + pageable.getPageSize()), tickets.size());
        Page<TicketEntity> ticketEntityPage = new PageImpl<>(tickets.subList(start, end), pageable, tickets.size());
        return ticketEntityPage;
    }

    private TicketEntity getTicketEntity() {
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setTicketId(1);
        ticketEntity.setTicketPrice(1000.00);
        ticketEntity.setMatchId(1);
        ticketEntity.setActive(true);
        ticketEntity.setCustomerName("test");

        return ticketEntity;
    }

    private TicketRequestModel getTicketRequestModel() {
        TicketRequestModel ticketRequestModel = new TicketRequestModel();
        ticketRequestModel.setTicketPrice(1000.00);
        ticketRequestModel.setMatchId(1);
        ticketRequestModel.setCustomerName("test");
        return ticketRequestModel;
    }

    private List<TicketEntity> getTicketEntities() {
        List<TicketEntity> tickets = new ArrayList<>();
        for(int x=0; x<2; x++) {
            TicketEntity ticketEntity = new TicketEntity();
            ticketEntity.setCustomerName("test");
            ticketEntity.setTicketPrice(1000.00 + x);
            ticketEntity.setTicketId(x+1);
            ticketEntity.setMatchId(x+1);
            tickets.add(ticketEntity);
        }
        return tickets;
    }

    private MatchResponseModel getMatchResponseModel() {
        MatchResponseModel matchResponseModel = new MatchResponseModel();
        matchResponseModel.setDateTime("");
        matchResponseModel.setMatchId(1);
        return matchResponseModel;
    }
}
