package com.stan.stancommons.flight.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Markup {
    private String markupType;
    private BigDecimal markup_value;
}
