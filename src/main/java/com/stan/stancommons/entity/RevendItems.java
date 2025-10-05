package com.stan.stancommons.entity;

import com.systemspecs.remita.vending.vendingcommon.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RevendItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productCode;
    private String accountNumber;
    private String phoneNumber;
    private String bulkRevendReferenceId;
    private String paymentIdentifier;
    private String message;
    private Date createdAt;
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    private TransactionStatus vendStatus;
}
