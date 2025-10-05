package com.stan.stancommons.eventsticketing.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class EventBookingResponseData {
    private long id;
    private String bookingId;
    private Integer ticketQuantity;
    private BigDecimal ticketAmount;
    private BigDecimal discountAmount;
    private BigDecimal amountDue;
    private Boolean isFree;
    private String productReference;
    private String bookingRef;
    private String session;
    private String paymentRef;
    private Date createdAt;
    private Date expiresAt;
}
