package com.stan.stancommons.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VerifyRequest {
    private String uniqueIdentifier;
    private String productCode;
    private BigDecimal amount;
}
