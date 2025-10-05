package com.stan.stancommons.movies.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MovieBookingResponseData {
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

    private String productCode;
    private String name;
    private String email;
    private String phone;
    private String movieName;
    private String cinemaName;
}
