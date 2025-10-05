package com.stan.stancommons.dto.request;

import lombok.Data;

@Data
public class BulkTransactionSearchCriteria {
    private String productCode;
    private String categoryCode;
    private String accountNumber;
    private String phoneNumber;
    private String bulkClientReference;
    private String vendStatus;

}
