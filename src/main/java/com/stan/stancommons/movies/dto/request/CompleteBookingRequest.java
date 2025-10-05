package com.stan.stancommons.movies.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompleteBookingRequest {
    private String productCode;
    private String name;
    private String email;
    private String phone;
    private String session;
    private String movieName;
    private String cinemaName;
}
