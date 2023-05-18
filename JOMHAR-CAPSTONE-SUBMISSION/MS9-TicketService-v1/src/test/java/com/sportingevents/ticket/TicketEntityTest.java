package com.sportingevents.ticket;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TicketEntityTest {

    @Test
    public void whenTicketInitialized_returnTicket() {
        TicketEntity expected = new TicketEntity();
        expected.setTicketId(1);
        expected.setTicketPrice(100.00);
        expected.setCustomerName("test");
        expected.setMatchId(1);
        expected.setActive(true);

        TicketEntity actual = getTicket();

        Assert.assertEquals(expected.getTicketId(), actual.getTicketId());
        Assert.assertEquals(expected.getTicketPrice(), actual.getTicketPrice());
        Assert.assertEquals(expected.getCustomerName(), actual.getCustomerName());
        Assert.assertEquals(expected.getMatchId(), actual.getMatchId());
        Assert.assertEquals(expected.getActive(), actual.getActive());
    }

    @Test(expected = NullPointerException.class)
    public void givenNullTicketId_whenInitialized_returnException() {
        TicketEntity ticket = new TicketEntity();
        ticket.setTicketId(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullTicketPrice_whenInitialized_returnException() {
        TicketEntity ticket = new TicketEntity();
        ticket.setTicketPrice(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullCustomerName_whenInitialized_returnException() {
        TicketEntity ticket = new TicketEntity();
        ticket.setCustomerName(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullMatchId_whenInitialized_returnException() {
        TicketEntity ticket = new TicketEntity();
        ticket.setMatchId(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullActive_whenInitialized_returnException() {
        TicketEntity ticket = new TicketEntity();
        ticket.setActive(null);
    }

    private TicketEntity getTicket() {
        TicketEntity ticket = new TicketEntity();
        ticket.setTicketId(1);
        ticket.setTicketPrice(100.00);
        ticket.setCustomerName("test");
        ticket.setMatchId(1);
        ticket.setActive(true);
        return ticket;
    }
}
