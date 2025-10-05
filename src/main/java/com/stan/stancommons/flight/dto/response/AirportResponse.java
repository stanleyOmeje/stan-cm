package com.stan.stancommons.flight.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AirportResponse {
    private String code;
    private String message;
    private List<AirportData> data;
}
