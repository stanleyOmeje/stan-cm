package com.stan.stancommons.movies.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class CompleteMovieBookingRequest {
    private String paymentIdentifier;
    @NotBlank
    private String bookingId;
    private String productCode;
    private String session;
    private String bookingRef;
    private String paymentRef;
    private BigDecimal amount;
}
