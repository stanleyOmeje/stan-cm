package com.stan.stancommons.flight.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResponsePriceSummary {
    private String passengerType;
    private Integer quantity;
    private BigDecimal totalPrice;
}
