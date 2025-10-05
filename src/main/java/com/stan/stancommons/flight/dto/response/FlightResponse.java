package com.stan.stancommons.flight.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class FlightResponse {
    private String code;
    private String message;
    List<FlightData> data;
    //private boolean doFallBack = false;

    public FlightResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
