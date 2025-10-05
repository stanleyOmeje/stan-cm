package com.stan.stancommons.flight.dto.response;

import lombok.Data;

@Data
public class Passengers {
    private String dob;
    private ResponseDocuments documents;
    private String email;
    private String firstName;
    private String gender;
    private String lastName;
    private String passengerType;
    private String phoneNumber;
    private String title;
    }

