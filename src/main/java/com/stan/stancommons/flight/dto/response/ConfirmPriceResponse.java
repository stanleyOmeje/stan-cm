package com.stan.stancommons.flight.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ConfirmPriceResponse {
    private String code;
    private String message;
    private FlightData data;
    private String bookingCode;

    public ConfirmPriceResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
