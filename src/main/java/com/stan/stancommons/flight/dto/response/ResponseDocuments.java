package com.stan.stancommons.flight.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseDocuments {
    private String documentType;
    private Date expiryDate;
    private boolean holder;
    private String issuingCountry;
    private Date issuingDate;
    private String nationalityCountry;
    private String number;
}
