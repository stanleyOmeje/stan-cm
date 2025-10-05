package com.stan.stancommons.eventsticketing.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CompleteEventBookingRequest {
    private String paymentIdentifier;
    @NotBlank
    private String bookingId;
    private String productCode;
    private String session;
    private String bookingRef;
    private String paymentRef;
    @NotNull
    private BigDecimal amount;
    private String movieSlug;
    private String cenemaSlug;
}
