package com.stan.stancommons.movies.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingRequest {
    private String productCode;
    private String name;
    private String cinemaName;
    private String movieName;
    private String phone;
    private String email;
    private String address;
    private Long showtimeId;
    private List<Ticket> tickets;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Ticket {
        private Long ticketId;
        private String ticketName;
        private Integer quantity;
        private Integer price;
    }
}
