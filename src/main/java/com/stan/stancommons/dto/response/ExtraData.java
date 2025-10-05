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
public class ExtraData {
    private String kct1;
    private String kct2;
    private String bsstTokenValue;
    private String standardTokenValue;
    private String bsstTokenUnits;
    private String standardTokenUnits;
    private String pin;
    private String meterNumber;
    private String customerName;
    private String receiptNumber;
    private String tariffClass;
    private BigDecimal amountPaid;
    private BigDecimal costOfUnit;
    private BigDecimal amountForDebt;
    private String unitsType;
    private BigDecimal accountBalance;
    private String mapToken1;
    private String mapToken2;
    private String mapUnits;
    private String tariffRate;
    private String address;
    private BigDecimal vat;
    private String message;
    private String unitsPurchased;
    private String accountType;
    private BigDecimal minVendAmount;
    private BigDecimal maxVendAmount;
    private BigDecimal remainingDebt;
    private String meterType;

    private BigDecimal replacementCost;
    private BigDecimal outstandingDebt;
    private BigDecimal administrativeCharge;
    private BigDecimal fixedCharge;
    private BigDecimal lossOfRevenue;
    private BigDecimal penalty;
    private BigDecimal meterServiceCharge;
    private BigDecimal meterCost;

    private BigDecimal ApplicationFee;
    private BigDecimal ReadingText;
    private BigDecimal CBTRegistrationCharge;
    private BigDecimal CBTExaminationCharge;
    private BigDecimal OptionalMock;
    private String strisBrilliant;

    private BigDecimal mapAmount;
    private BigDecimal refundAmount;
    private String refundUnits;
    private String account;
//new fields
    private Long districtCode;
    private String districtName;
    private String billType;
    private String billTypeDesc;
    private String paymentType;
    private BigDecimal paymentAmount;


    public ExtraData(String kct1, String kct2, String bsstTokenValue, String standardTokenValue) {
        this.kct1 = kct1;
        this.kct2 = kct2;
        this.bsstTokenValue = bsstTokenValue;
        this.standardTokenValue = standardTokenValue;

    }


}
