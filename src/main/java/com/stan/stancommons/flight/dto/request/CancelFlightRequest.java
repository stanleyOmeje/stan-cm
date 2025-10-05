package com.stan.stancommons.flight.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CancelFlightRequest {
    private String bookingReference;
}
