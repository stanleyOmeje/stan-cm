package com.stan.stancommons.flight.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceData {
    private BigDecimal balance;
    private String unit;
}
