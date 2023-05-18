package com.sportingevents.ticket;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class TicketResponseModel {

    @NonNull
    private Integer ticketId;

    @NonNull
    private String customerName;

    @NonNull
    private Double ticketPrice;

    @NonNull
    private Integer matchId;
}
