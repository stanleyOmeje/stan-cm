package com.stan.stancommons.flight.entity;

import com.systemspecs.remita.vending.vendingcommon.flight.enums.BookingStatus;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class BookingValidation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String flightId;
    private BigDecimal amount;
    private String productCode;
    private int numberOfAdult;
    private int numberOfChildren;
    private int numberOfInfant;
    private String bookingCode;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    private Date createdAt;
    private Date updatedAt;
}
