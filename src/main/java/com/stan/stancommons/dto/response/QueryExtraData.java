package com.stan.stancommons.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueryExtraData {
    private String kct1;
    private String kct2;
    private String bsstTokenValue;
    private String standardTokenValue;
    private String bsstTokenUnits;
    private String standardTokenUnits;
    private String pin;
    private String meterNumber;
    private String customerName;
    private BigDecimal amountPaid;
    private BigDecimal accountBalance;
    private String unitsPurchased;
    private String meterType;

    private BigDecimal remainingDebt;
    private String address;
    private BigDecimal vat;
    private String message;
    private String accountType;
    private BigDecimal outstandingDebt;
    private String tariffClass;
    private BigDecimal costOfUnit;
    private String tariffRate;

    public QueryExtraData(String kct1, String kct2, String bsstTokenValue, String standardTokenValue) {
        this.kct1 = kct1;
        this.kct2 = kct2;
        this.bsstTokenValue = bsstTokenValue;
        this.standardTokenValue = standardTokenValue;

    }
}
