package com.stan.stancommons.dto.request;

import com.systemspecs.remita.vending.vendingcommon.enums.ProductType;
import lombok.Data;

@Data
public class ProductSearchCriteria {
    private String country;
    private String countryCode;
    private String categoryCode;
    private ProductType productType;
    private String currencyCode;
    private String provider;
    private String code;
}
