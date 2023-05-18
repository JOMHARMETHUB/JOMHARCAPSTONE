package com.sportingevents.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportingevents.common.constant.ApiEndpoint;
import com.sportingevents.match.MatchResponseModel;
import com.sportingevents.match.MatchRestService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TicketControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TicketController ticketController;

    @Mock
    private TicketServiceImpl ticketService;

    @Mock
    private MatchRestService matchRestService;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(ticketController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void givenAuthenticatedUser_whenGetAllTickets_returnTickets() throws Exception {
        List<TicketResponseModel> tickets = getAllTickets();
        when(ticketService.getAllTickets(any(Pageable.class))).thenReturn(tickets);
        mockMvc.perform(MockMvcRequestBuilders.get(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TICKETS))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(tickets)));
    }

    @Test
    public void givenAuthenticatedUser_whenGetTicket_returnTicket() throws Exception {
        TicketResponseModel ticket = getTicket();
        when(ticketService.getTicket(any(Integer.class))).thenReturn(ticket);
        mockMvc.perform(MockMvcRequestBuilders.get(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TICKETS + ApiEndpoint.ID_PATH_VARIABLE, "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(ticket)));
    }

    @Test
    public void givenAuthenticatedUserAndValidRequestBody_whenSaveTicket_returnMessage() throws Exception {
        when(matchRestService.getMatch(anyInt(), anyString())).thenReturn(ResponseEntity.ok(mock(MatchResponseModel.class)));
        when(ticketService.saveTicket(any(TicketRequestModel.class))).thenReturn(TicketMessage.TICKET_SAVE_SUCCESS);
        mockMvc.perform(MockMvcRequestBuilders.post(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TICKETS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getTicketRequestModel()))
                        .header(HttpHeaders.AUTHORIZATION, "test"))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().string(TicketMessage.TICKET_SAVE_SUCCESS));
    }

    @Test
    public void givenAuthenticatedUserAndValidRequestBodyAndTicketId_whenUpdateTicket_returnMessage() throws Exception {
        when(matchRestService.getMatch(anyInt(), anyString())).thenReturn(ResponseEntity.ok(mock(MatchResponseModel.class)));
        when(ticketService.updateTicket(any(Integer.class), any(TicketRequestModel.class))).thenReturn(TicketMessage.TICKET_UPDATE_SUCCESS);
        mockMvc.perform(MockMvcRequestBuilders.put(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TICKETS + ApiEndpoint.ID_PATH_VARIABLE, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getTicketRequestModel()))
                        .header(HttpHeaders.AUTHORIZATION, "test"))
                .andExpect(status().isOk())
                .andExpect(content().string(TicketMessage.TICKET_UPDATE_SUCCESS));
    }

    @Test
    public void givenAuthenticatedUserAndValidTicketId_whenDeleteTicket_returnMessage() throws Exception {
        when(ticketService.deleteTicket(any(Integer.class))).thenReturn(TicketMessage.TICKET_DELETE_SUCCESS);
        mockMvc.perform(MockMvcRequestBuilders.delete(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TICKETS + ApiEndpoint.ID_PATH_VARIABLE, "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(TicketMessage.TICKET_DELETE_SUCCESS));
    }

    private TicketRequestModel getTicketRequestModel() {
        TicketRequestModel ticketRequestModel = new TicketRequestModel();
        ticketRequestModel.setTicketPrice(1000.00);
        ticketRequestModel.setMatchId(1);
        ticketRequestModel.setCustomerName("test");
        return ticketRequestModel;
    }

    private List<TicketResponseModel> getAllTickets() {
        List<TicketResponseModel> ticketResponseModels = new ArrayList<>();
        for(int x=0; x<2; x++) {
            TicketResponseModel ticketResponseModel = new TicketResponseModel();
            ticketResponseModel.setTicketPrice(1000.00 + x);
            ticketResponseModel.setTicketId(x+1);
            ticketResponseModel.setCustomerName("custname");
            ticketResponseModel.setMatchId(1);
            ticketResponseModels.add(ticketResponseModel);
        }

        return ticketResponseModels;
    }

    private TicketResponseModel getTicket() {
        TicketResponseModel ticketResponseModel = new TicketResponseModel();
        ticketResponseModel.setTicketPrice(1000.00);
        ticketResponseModel.setTicketId(1);
        ticketResponseModel.setCustomerName("custname");
        ticketResponseModel.setMatchId(1);
        return ticketResponseModel;
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
