package com.stan.stancommons.flight.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AirlineDetailsResponse {
    private List<AirlineDetails> airlineDetails;
}
