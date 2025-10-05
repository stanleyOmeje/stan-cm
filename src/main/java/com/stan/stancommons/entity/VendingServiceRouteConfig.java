package com.stan.stancommons.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
public class VendingServiceRouteConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String productCode;

   private String processorId;

    private boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    private boolean enableFallBackProcessor;

    private String fallBackProcessorId;

    private String systemProductType;
}
