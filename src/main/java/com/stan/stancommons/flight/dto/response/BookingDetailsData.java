package com.stan.stancommons.flight.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BookingDetailsData {
    private BigDecimal amount;
    private String createdAt;
    private String currency;
    private boolean documentRequired;
    private String expiresAt;
    private Bounds inbound;
    private Bounds outbound;
    private Integer outboundStops;
    private List<Passengers> passengersList = new ArrayList<>();
    private List<ResponsePriceSummary> priceSummaryList = new ArrayList<>();
    private String reference;
    private String status;
    private Integer totalDuration;
    private Integer totalInboundDuration;
    private Integer totalOutboundDuration;
    private String ticketNumber;
}
