package com.stan.stancommons.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetServiceResponse {
    private String message;
    private String code;
    private Object data;

    public GetServiceResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public GetServiceResponse(String message, String code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }
}
