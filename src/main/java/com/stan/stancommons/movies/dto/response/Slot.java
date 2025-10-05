package com.stan.stancommons.movies.dto.response;

import lombok.Data;

@Data
public class Slot {
    private String time;
    private SlotData data;   // some slots wrap everything in "data"
    private Integer seats_left; // for slots without "data"
    private java.util.List<Ticket> tickets; // for slots without "data"
}
