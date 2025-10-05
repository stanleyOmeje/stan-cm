package com.stan.stancommons.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Data
@Getter
@Setter
public class ProcessorPackageRequest {

    private boolean active = true;

    @NotBlank
    private String productCode;

    private String serviceCode;

    private String serviceCategory;

    private String serviceName;

    private String serviceType;

    private String addonCode;

    private String variationCode;

    private String serviceId;
    private int productId;

    private String paymentPlan;
    private String paymentMode;
    private String channel;
    private String billType;
    private String categoryId;
    private String productName;
    private boolean autoConfirm;
    private String productType;
    private String merchantId;

    private String lookupTransactionType;
    private String paymentTransactionType;
    private String vasId;

    private String countryCode;
    private String subscriptionId;
    private String gateKey;
    private String serviceProvider;
    private String mCode;
    private String mPin;
    private String loginId;
    private String protectedKey;
    private String accountId;
    private String password;


}
