package com.stan.stancommons.flight.dto.response;


import lombok.Data;

@Data
public class AirportDetails {
    private String city;
    private String cityCode;
    private String country;
    private String countryCode;
    private String iataCode;
    private String name;
}
