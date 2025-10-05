package com.stan.stancommons.eventsticketing.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class EventTicketBookingRequest {
    private String productCode;
    private String name;
    private String email;
    private String phone;
    private String listingId;
    private String location;
    private List<EventTicket> ticketList;

}
