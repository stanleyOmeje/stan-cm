package com.stan.stancommons.movies.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class MovieTicketBookingRequest {
    private Long showtimeId;
    private List<Ticket> tickets;

    @Data
    public static class Ticket {
        private Long ticketId;
        private String ticketName;
        private Integer quantity;
        private Double price;
    }
}
