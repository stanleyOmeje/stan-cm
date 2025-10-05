package com.stan.stancommons.flight.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BookingDetailsResponse {
    private String code;
    private String message;
    private BookingDetailsData data;
    //private boolean doFallBack = false;

    public BookingDetailsResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
