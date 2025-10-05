package com.stan.stancommons.dto.request;

import lombok.Data;

@Data
public class ResponseCodeMappingRequest {
    private String processorId;
    private String processorResponseCode;
    private String processorResponseMessage;
    private String standardResponseCode;
    private String standardResponseMessage;
    private String customMessage;
}
