package com.stan.stancommons.flight.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class BookFlightRequest {
    @NotBlank
    private String paymentIdentifier;
    @NotBlank
    private String bookingCode;
    private String flightId;
    private List<Passenger> adult = new ArrayList<>();
    private List<Passenger> children = new ArrayList<>();
    private List<Passenger> infant = new ArrayList<>();
}
