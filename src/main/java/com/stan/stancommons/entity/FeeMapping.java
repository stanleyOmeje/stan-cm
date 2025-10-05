package com.stan.stancommons.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.systemspecs.remita.vending.vendingcommon.enums.FeeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeeMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Enumerated(EnumType.STRING)
    private FeeType feeType;

    private BigDecimal amount;

    @JsonIgnore
    @OneToOne
    private Product product;

    @JsonIgnore
    private Date createdAt;

    @JsonIgnore
    private Date updatedAt;
}
