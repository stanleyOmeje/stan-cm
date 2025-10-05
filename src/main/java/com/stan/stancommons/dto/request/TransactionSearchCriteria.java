package com.stan.stancommons.dto.request;

import com.systemspecs.remita.vending.vendingcommon.enums.TransactionStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionSearchCriteria {
    private TransactionStatus status;
    private String productCode;
    private String clientReference;
    private LocalDate startDate;
    private LocalDate endDate;
    private String internalReference;
    private String categoryCode;
    private String userId;
    private String ipAddress;
    private String merchantOrgId;
}
