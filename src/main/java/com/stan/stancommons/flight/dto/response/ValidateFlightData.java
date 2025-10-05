package com.stan.stancommons.flight.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ValidateFlightData {
    private BigDecimal price;
    private String bookingCode;
    private List<SummaryPrices> priceSummary;
}
