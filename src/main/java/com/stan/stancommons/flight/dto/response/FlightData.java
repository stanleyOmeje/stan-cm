package com.stan.stancommons.flight.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FlightData {
    private BigDecimal amount;
    private String currency;
    private String flightId;
    private String bookingAccount;
    private Bounds inbound;
    private Bounds outbound;
    private String externalReference;
    private Integer bookableSeats;
    private List<SummaryPrices> priceSummary;
    private Integer adults;
    private Integer children;
    private Integer infants;
}
