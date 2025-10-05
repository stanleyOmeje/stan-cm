package com.stan.stancommons.flight.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AirportDetailResponse {
    private List<AirportDetails> airportDetailsList;
}
