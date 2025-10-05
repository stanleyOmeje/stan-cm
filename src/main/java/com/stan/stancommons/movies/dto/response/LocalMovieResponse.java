package com.stan.stancommons.movies.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LocalMovieResponse {
    private String bookingId;
    private Integer ticketQuantity;
    private BigDecimal ticketAmount;
    private String bookingRef;
    private Date createdAt;
    private Date expiresAt;
}
