package com.stan.stancommons.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class ExtraToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


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
    private String unitsPurchased;
    private String message;
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


    @JsonIgnore
    @OneToOne
    private Transaction transaction;

    private Long districtCode; //district code
    private String districtName;
    private String billType;
    private String billTypeDesc;
    private String paymentType;
    private BigDecimal paymentAmount;

}
