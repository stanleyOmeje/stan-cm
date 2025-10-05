package com.stan.stancommons.eventsticketing.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class EventBookingResponse {
    private String message;
    private String code;
    private EventBookingResponseData data;

    public EventBookingResponse() {
    }

    public EventBookingResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
