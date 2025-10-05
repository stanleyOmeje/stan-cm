package com.stan.stancommons.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomCommissionCreateRequest {

    private Boolean isAppliedCommission;

    private Boolean isFixedCommission;

    private BigDecimal fixedCommission;

    private String percentageCommission;
    private BigDecimal percentageMax;

    private BigDecimal percentageMin;

    private String merchantId;
    private String productCode;
    private String processor;
    private Boolean isPlatformCommission;
}
