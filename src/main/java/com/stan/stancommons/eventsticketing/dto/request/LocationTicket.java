package com.stan.stancommons.eventsticketing.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class LocationTicket {
    private int quantity;
    private String ticketId;
    private List<Attendees> attendees;
    private List<Activities> activitiesList;
}
