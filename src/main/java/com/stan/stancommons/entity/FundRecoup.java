package com.stan.stancommons.entity;

import com.systemspecs.remita.vending.vendingcommon.enums.Action;
import com.systemspecs.remita.vending.vendingcommon.enums.DebitStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class FundRecoup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String reference;
    @Enumerated(EnumType.STRING)
    private DebitStatus status;
    private Date createdAt;
    private Date debitedDate;
    private String message;
    @Enumerated(EnumType.STRING)
    private Action action;
}
