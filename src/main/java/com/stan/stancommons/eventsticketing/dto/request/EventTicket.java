package com.stan.stancommons.eventsticketing.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class EventTicket {
    private int quantity;
    private String ticketId;
    private List<Attendees> attendees;
    private List<Items> activitiesList;
}
