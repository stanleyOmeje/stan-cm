package com.stan.stancommons.flight.dto.request;


import com.systemspecs.remita.vending.vendingcommon.enums.CabinType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SearchFlightRequest {
   // @NotBlank
    private String productCode;
    @NotNull
    @Enumerated(EnumType.STRING)
    private CabinType cabin;
    private Integer adults;
    @NotBlank
    private String departureDate;
    @NotBlank
    private String destinationCityCode;
    @NotBlank
    private String originCityCode;
    private Integer children;
    private Integer infants;
    private String returnDate;
}
