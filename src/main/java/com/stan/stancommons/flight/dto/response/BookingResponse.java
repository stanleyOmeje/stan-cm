package com.stan.stancommons.flight.dto.response;

import lombok.Data;

@Data
public class BookingResponse {
    private String bookingCode;
    private String paymentIdentifier;
}
