package com.stan.stancommons.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class CustomCommission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productCode;

    private BigDecimal percentageMax;

    private BigDecimal percentageMin;

    private Boolean isFixedCommission;

    private BigDecimal fixedCommission;

    private String percentageCommission;

    @Column(nullable = false)
    private String merchantId;

    @Column(nullable = false)
    private Boolean isAppliedCommission;

    private String processor;

    private Boolean isPlatformCommission;

    private Date createdAt;

    private Date updatedAt;

}
