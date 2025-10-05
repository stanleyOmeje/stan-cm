package com.stan.stancommons.flight.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class FlightBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    private String productCode;
    @JsonIgnore
    private String processorId;
    private String categoryCode;
    private String internalReference;
    private String bookingIdentifier;

    private BigDecimal amount;
    private String currency;
    private String offerId;
    private String externalReference;
    private String bookingAccount;
    private int adult;
    private int children;
    private int infants;
    private Date createdAt;
    private Date updateddAt;

    private String userId;
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
}
