package com.stan.stancommons.movies.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateMovieBookingRequest {
    private String productCode;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Long showtimeId;
    private String cinemaName;
    private String movieName;
    private String session;
    private List<CreateMovieBokingTicket> tickets;

}
