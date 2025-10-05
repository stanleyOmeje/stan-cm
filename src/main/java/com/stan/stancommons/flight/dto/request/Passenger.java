package com.stan.stancommons.flight.dto.request;

import lombok.Data;

@Data
public class Passenger {
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String title;
    private String email;
    private String phoneNumber;
    private String city;
    private String country;
    private String countryCode;
    private String postalCode;
    private String middleName;
    private String address;
    private FlightDocument documents;
    private Luggage luggage;
}
