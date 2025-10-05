package com.stan.stancommons.flight.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PriceSummary {
    private List<SummaryPrices> priceSummary;
}
