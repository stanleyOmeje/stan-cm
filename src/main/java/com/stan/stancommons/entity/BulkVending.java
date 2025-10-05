package com.stan.stancommons.entity;

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
public class BulkVending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientReference;

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private VendStatus vendStatus;

    private boolean processed;
    private String profileId;

}
