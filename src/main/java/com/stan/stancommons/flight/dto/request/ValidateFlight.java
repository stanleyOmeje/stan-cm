package com.stan.stancommons.flight.dto.request;


import lombok.Data;

@Data
public class ValidateFlight {
    private String productCode;
    private String flightId;
}
