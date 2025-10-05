package com.stan.stancommons.flight.dto.request;

import lombok.Data;

@Data
public class FlightDocument {
    private String number;
    private String issuingDate;
    private String expiryDate;
    private String issuingCountry;
    private String nationalityCountry;
    private String documentType;
    private boolean holder;
}
