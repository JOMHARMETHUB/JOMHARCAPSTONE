package com.sportingevents.ticket;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "tickets", schema = "sporting_events_ticket_schema")
@NoArgsConstructor
public class TicketEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ticket_id")
    private Integer ticketId;

    @NonNull
    @Column(name = "customer_name")
    private String customerName;

    @NonNull
    @Column(name = "ticket_price")
    private Double ticketPrice;

    @NonNull
    @Column(name = "active")
    private Boolean active = true;

    @NonNull
    @Column(name = "match_id")
    private Integer matchId;


}
