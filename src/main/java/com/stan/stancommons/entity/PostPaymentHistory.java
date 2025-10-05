package com.stan.stancommons.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PostPaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String postPaymentRef;
    private String merchantAccountNumber;
    private String merchantBankCode;
    private LocalDateTime paymentDate;
    private BigDecimal totalAmount;

}
