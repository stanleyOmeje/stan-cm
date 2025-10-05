package com.stan.stancommons.flight.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class ConvertCurrencyRequest {
     private String fromCurrency;
    private String toCurrency;
    private BigDecimal amount;
    private Date date;
}
