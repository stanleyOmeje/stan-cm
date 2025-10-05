package com.stan.stancommons.movies.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CreateBookingsResponse {
    private String message;
    private String code;
    private MovieBookingResponseData data;

    public CreateBookingsResponse() {
    }

    public CreateBookingsResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
