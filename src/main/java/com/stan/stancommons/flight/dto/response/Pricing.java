package com.stan.stancommons.flight.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Pricing {
    private String baseFare;
    private Markup markup;
    private BigDecimal payable;
    private String tax;
}
