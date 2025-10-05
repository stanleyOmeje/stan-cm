package com.stan.stancommons.eventsticketing.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ListingResponse {
    private String code;
    private String message;
    private ListingResponseData data;

    public ListingResponse() {
    }

    public ListingResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
