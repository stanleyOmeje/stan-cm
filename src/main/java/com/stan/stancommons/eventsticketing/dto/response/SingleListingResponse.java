package com.stan.stancommons.eventsticketing.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SingleListingResponse {
    private String code;
    private String message;
    private SingleListingData data;


    public SingleListingResponse() {
    }

    public SingleListingResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
