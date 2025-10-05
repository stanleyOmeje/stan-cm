package com.stan.stancommons.entity;

import com.systemspecs.remita.vending.vendingcommon.enums.PostpaidSettlementStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class PostpaidSettlement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PostpaidSettlementStatus  status = PostpaidSettlementStatus.UNSETTLED;
    private BigDecimal amount;
    private String merchantAccountNumber;
    private String merchantOrgId;
    private String merchantBankCode;
    private String postPaymentRef;
    private Date createdAt;
    private String ipAddress;
    private String tenantId;
    private String processorId;
    private String productCode;
    private String clientReference;
}
