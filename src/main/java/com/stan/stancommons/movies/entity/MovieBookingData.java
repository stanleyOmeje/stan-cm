package com.stan.stancommons.movies.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class MovieBookingData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String bookingId;
   // private int ticketQuantity;
    private BigDecimal ticketAmount;
    private BigDecimal discountAmount;
    private BigDecimal amountDue;
    private Boolean isFree;
    private String productReference;
    private String bookingRef;
    private String session;
    private String paymentRef;
    private Date createdAt;
    private Date updatedAt;
    private Date expiresAt;
    private String productCode;
    private String name;
    private String email;
    private String phone;
    private String movieName;
    private String cinemaName;
}
