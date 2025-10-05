package com.stan.stancommons.eventsticketing.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EventDto {
    private String userId;
    private String internalReference;
    private String mainResponseMessage;
    private String merchantName;
    private String merchantOrgId;
    private String accountNumber;
    private String ipAddress;
    private String bankCode;
    private String subscriptionType;
    private String tenantId;
    private BigDecimal discountedAmount;
    private BigDecimal commission;
    private String caller;
    private String serviceName;
    private String orgId ;
    private String fundingType;
    private String service;
    private String productCode;

    private boolean processedWithFallback;
    private String fallbackProcessorId;
    private String fallbackResponseMessage;

}
