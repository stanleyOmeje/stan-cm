package com.stan.stancommons.eventsticketing.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LocationBookingResponse {
    private String code;
    public String message;
    public LocationBookingResponseData data;
}
