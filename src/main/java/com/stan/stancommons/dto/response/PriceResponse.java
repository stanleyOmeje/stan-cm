package com.stan.stancommons.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PriceResponse {
    private String productId;
    private BigDecimal fixedRetailPrice;
    private String fixedRetailUnit;

    private BigDecimal fixedDestinationPrice;
    private String fixedDestinationUnit;

    private BigDecimal fixedWholeSalePrice;
    private String fixedWholeSaleUnit;

    private BigDecimal rangedMinDestinationAmount;
    private BigDecimal rangedMaxDestinationAmount;
    private String rangedDestinationUnit;

    private BigDecimal rangedMinWholeSaleAmount;
    private BigDecimal rangedMaxWholeSaleAmount;
    private String rangedWholeSaleUnit;

    private String currentExchangeBaseRate;
    private String currentExchangeRetailRate;
    private String productType;
    private Date currentDate;
    private String currency;

}
