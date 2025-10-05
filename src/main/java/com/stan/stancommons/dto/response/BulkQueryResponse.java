package com.stan.stancommons.dto.response;

import com.systemspecs.remita.vending.vendingcommon.enums.TransactionStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
public class BulkQueryResponse {
    private String productCode;
    private String categoryCode;
    private String accountNumber;
    private String phoneNumber;
    private BigDecimal amount;
    private String bulkClientReference;
    private String internalReference;

    @Enumerated(EnumType.STRING)
    private TransactionStatus vendStatus;

}
