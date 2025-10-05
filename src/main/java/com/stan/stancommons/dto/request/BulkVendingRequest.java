package com.stan.stancommons.dto.request;

import com.systemspecs.remita.vending.vendingcommon.enums.VendStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@Setter
@Builder
public class BulkVendingRequest {
    private String clientReference;
    private BigDecimal totalAmount;
    private VendStatus vendStatus;
    private List<VendingItemsRequest> items;
    private String profileId;

}


