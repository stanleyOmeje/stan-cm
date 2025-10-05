package com.stan.stancommons.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.systemspecs.remita.vending.vendingcommon.enums.ProductType;
import com.systemspecs.remita.vending.vendingcommon.enums.SystemProduct;
import com.systemspecs.remita.vending.vendingcommon.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private BigDecimal amount;

    @JsonIgnore
    private String userId;

    private String productCode;

    @JsonIgnore
    private String processorId;

    private String categoryCode;

    @JsonProperty("reference")
    private String internalReference;

    @JsonIgnore
    private String externalReference;

    private String clientReference;

    private boolean isBulkVending;

    private boolean isReVend;

    @JsonProperty("token")
    private String token;

    @JsonProperty("units")
    private String units;

    @JsonProperty("receiptNumber")
    private String receiptNumber;

    @JsonProperty("tax")
    private String tax;

    @JsonProperty("tariff")
    private String tariff;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private String responseMessage;
    private String responseCode;

    @JsonIgnore
    private int retrialCount;

    @JsonIgnore
    private int retrialTimeInSec;

    @JsonProperty("metadata")
    @OneToOne(fetch = FetchType.EAGER)
    private TransactionData transactionData;

    @JsonIgnore
    private Boolean processedWithFallback;

    @JsonIgnore
    private String fallbackProcessorId;

    @JsonIgnore
    private String fallbackResponseMessage;
    private String fallbackResponseCode;
    private String processorResponseCode;

    @JsonIgnore
    private String processorResponseMessage;

    private Date createdAt;

    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private String countryIsoCode;
    private String country;
    private String currencyCode;
    private boolean isInternational;

    private Date confirmationDate;

    @OneToOne(fetch = FetchType.EAGER)
    private ExtraToken extraToken;

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
    private String provider;

    @Enumerated(EnumType.STRING)
    private SystemProduct systemProductType;

    private String merchantPercentageCommission;
    private BigDecimal merchantMinAmountCap;
    private BigDecimal merchantMaxAmountCap;
    private String platformPercentageCommission;
    private BigDecimal platformMinAmountCap;
    private BigDecimal platformMaxAmountCap;
    private BigDecimal platformCommission;

    @JsonProperty("retrialIntervalInSec")
    public int getRetrialIntervalInSec() {
        return status != TransactionStatus.PENDING ? 0 : retrialCount + 30;
    }

    @JsonIgnore
    public LocalDateTime getRetrialTime() {
        Date lastUpdatedTime = updatedAt == null ? createdAt : updatedAt;
        LocalDateTime ldt = LocalDateTime.ofInstant(lastUpdatedTime.toInstant(), ZoneId.systemDefault());
        return ldt.plusSeconds(retrialCount);
    }

    private boolean submittedForReversals;
    private boolean reversalSuccessful;

}
