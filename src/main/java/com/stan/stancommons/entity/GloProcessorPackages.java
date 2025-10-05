package com.stan.stancommons.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
public class GloProcessorPackages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean active;

    private String cisProductName;

    private String cisPlanName;

    private String validity;

    private String ersPlanId;

    private String ersPlanAmount;

    @Column(unique = true)
    private String productCode;

    private Date dateCreated;

}
