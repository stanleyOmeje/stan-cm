package com.stan.stancommons.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private String userId;

    private String internalReference;

    private Boolean processedWithFallback;

    public TransactionDTO(String userId, String internalReference, Boolean processedWithFallback, String fallbackProcessorId, String fallbackResponseMessage, String fallbackResponseCode, String mainResponseMessage) {
        this.userId = userId;
        this.internalReference = internalReference;
        this.processedWithFallback = processedWithFallback;
        this.fallbackProcessorId = fallbackProcessorId;
        this.fallbackResponseMessage = fallbackResponseMessage;
        this.fallbackResponseCode = fallbackResponseCode;
        this.mainResponseMessage = mainResponseMessage;
    }

    private String fallbackProcessorId;

    private String fallbackResponseMessage;
    private String fallbackResponseCode;

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
    private BigDecimal platformAmount;
    private BigDecimal distributorAmount;
    private String merchantPercentageCommission;
    private BigDecimal merchantMinAmount;
    private BigDecimal merchantMaxAmount;
    private String platformPercentageCommission;
    private BigDecimal platformMinAmount;
    private BigDecimal platformMaxAmount;
    private BigDecimal platformCommission;




    public TransactionDTO(String userId, String internalReference, String mainResponseMessage) {
        this.userId = userId;
        this.internalReference = internalReference;
        this.mainResponseMessage = mainResponseMessage;
    }

}
