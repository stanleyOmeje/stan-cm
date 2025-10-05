package com.stan.stancommons.dto.request;

import lombok.Data;

@Data
public class CustomCommissionFilter {
    private String merchantId;
    private String productCode;
    private String processor;
}
