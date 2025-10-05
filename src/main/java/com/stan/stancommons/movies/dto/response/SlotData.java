package com.stan.stancommons.movies.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class SlotData {
    private int showtime_id;
    private String start_time;
    private String end_time;
    private String date;
    private String screen;
    private int seats_left;
    private List<Ticket> tickets;
}
