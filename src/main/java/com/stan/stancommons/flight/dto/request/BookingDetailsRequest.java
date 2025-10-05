package com.stan.stancommons.flight.dto.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BookingDetailsRequest {
    @NotBlank
    private String bookingCode;
}
