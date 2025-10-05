package com.stan.stancommons.flight.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Processors {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String processorId;
}
