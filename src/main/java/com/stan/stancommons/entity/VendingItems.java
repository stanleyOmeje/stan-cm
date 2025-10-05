package com.stan.stancommons.entity;

import com.systemspecs.remita.vending.vendingcommon.enums.TransactionStatus;
import com.systemspecs.remita.vending.vendingcommon.enums.VendStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendingItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productCode;
    private String categoryCode;
    private String accountNumber;
    private String phoneNumber;
    private BigDecimal amount;
    private String bulkClientReference;
    private String internalReference;

    private String merchantName;
    private String merchantId;
    private String merchantAccountNumber;
    private String ipAddress;
    private String bankCode;
    private String orgId;

    @Enumerated(EnumType.STRING)
    private TransactionStatus vendStatus;

    @Enumerated(EnumType.STRING)
    private VendStatus ValidationStatus;
    private boolean isBulkVending;

}
