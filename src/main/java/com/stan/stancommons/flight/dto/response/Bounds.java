package com.stan.stancommons.flight.dto.response;

import lombok.Data;

@Data
public class Bounds {
    private AirlineDetails airlineDetails;
    private String airportFrom;
    private AirportDetails airportFromDetails;
    private String airportTo;
    private AirportDetails airportToDetails;
    private String arrivalTime;
    private String baggage;
    private String cabinType;
    private String departureTime;
    private Long duration;
    private String flightNumber;
    private String operatingAirline;
}
