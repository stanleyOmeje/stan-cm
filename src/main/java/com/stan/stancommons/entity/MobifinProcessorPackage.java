package com.stan.stancommons.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity
public class MobifinProcessorPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean active;

    private String productCode;

    private String processorId;

    private String serviceCode;

    private String serviceCategory;

    private String serviceName;

    private String serviceType;

    private String addonCode;

    @JsonIgnore
    private Date createdAt;

    @JsonIgnore
    private Date updatedAt;

}
