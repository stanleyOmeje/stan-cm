package com.stan.stancommons.flight.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SummaryPrices {
    private String passengerType;
    private int quantity;
    private BigDecimal totalPrice;
}
