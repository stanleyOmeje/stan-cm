package com.stan.stancommons.movies.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class Showtime {
    private String date;
    private List<Slot> slots;
}
