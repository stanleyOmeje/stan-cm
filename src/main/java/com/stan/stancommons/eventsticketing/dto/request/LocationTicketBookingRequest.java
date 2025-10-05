package com.stan.stancommons.eventsticketing.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class LocationTicketBookingRequest {
    private String productCode;
    private String name;
    private String email;
    private String listing_id;
    private String location;
    private List<LocationTicket> ticketList;

}
