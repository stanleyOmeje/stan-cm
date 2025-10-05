package com.stan.stancommons.dto.request;

import com.systemspecs.remita.vending.vendingcommon.enums.VendStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Data
@Getter
@Setter
@Builder
public class VendingItemsRequest {
    private String productCode;
    private String accountNumber;
    private String phoneNumber;
    private BigDecimal amount;
    private String categoryCode;
    private VendStatus vendStatus;
    private boolean isBulkVending;
}
