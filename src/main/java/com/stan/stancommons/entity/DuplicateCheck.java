package com.stan.stancommons.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_client_ref_merchant_org", columnNames = {"clientReference", "merchantOrgId"})})
public class DuplicateCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private BigDecimal amount;
    private String productCode;
    @Column(nullable = false)
    private String clientReference;
    private Date createdAt;
    private String merchantName;
    private String merchantOrgId;
    private String ipAddress;

}
