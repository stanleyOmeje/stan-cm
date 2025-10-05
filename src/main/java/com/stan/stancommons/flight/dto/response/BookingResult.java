package com.stan.stancommons.flight.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.systemspecs.remita.vending.vendingcommon.enums.TransactionStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Data
public class BookingResult {
    private String code;
    private String message;
    private TransactionStatus status;
    private String bookingCode;
    private String productCode;
    private String categoryCode;
    private String internalReference;
    private String bookingIdentifier;
    private BigDecimal amount;
    private String currency;
    private String flightId;
    private String externalReference;
    private String bookingAccount;
    private int adult;
    private int children;
    private int infants;


    private boolean doFallBack = false;
    private String processorResponseCode;
    private String processorResponseMessage;

    private Date createdAt;
    private Date updateddAt;

    private Object validationResult;

    public BookingResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BookingResult(Object validationResult, String code, String message) {
        this.validationResult = validationResult;
        this.code = code;
        this.message = message;
    }
}
