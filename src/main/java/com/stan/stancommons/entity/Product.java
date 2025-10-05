package com.stan.stancommons.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.systemspecs.remita.vending.vendingcommon.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class Product {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String code;

    @OneToOne(mappedBy = "product")
    private FeeMapping fee;

    @JsonIgnore
    @ManyToOne
    private Category category;

    @JsonIgnore
    private Date createdAt;

    @JsonIgnore
    private Date updatedAt;

    @JsonProperty("categoryCode")
    public String categoryCode() {
        return category == null ? null : category.getCode();
    }

    private String country = "Nigeria";

    private String countryCode= "NGA";

    private String currencyCode = "NGN";

    private String calculationMode;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(nullable = false)
    private Boolean applyCommission = false;

    private BigDecimal fixedCommission;

    private String percentageCommission;

    private Boolean isFixedCommission;

    private BigDecimal percentageMaxCap;

    private BigDecimal percentageMinCap;

    private String provider;

    private boolean active;

}
