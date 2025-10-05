package com.stan.stancommons.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationData {
    private String name;

    private String accountNumber;

    private String phoneNumber;

    private String email;

    private String productCode;

    private String accountType;

    private BigDecimal minAmount;

    private BigDecimal maxAmount;

    private BigDecimal outstandingBalance;

    private String address;

    private String district;
    private String uniqueIdentifier;
    private String transactionRef;
    private String meterNumber;
    private String kct1;
    private String kct2;
    private List<String> vendorsAllowed;
    private String accountStatus;
    private String productPrice;
    private String productValue;
    private String tariffRate;
    private String tariff;
    private boolean candidateNotUnderAge;
    private String account;

}
