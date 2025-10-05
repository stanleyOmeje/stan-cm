package com.stan.stancommons.flight.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CurrencyData {
    private BigDecimal amount;
    private BigDecimal convertedAmount;
    private Date date;
    private String fromCurrency;
    private BigDecimal rate;
    private String toCurrency;
}
