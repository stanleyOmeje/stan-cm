package com.stan.stancommons.eventsticketing.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class EventListingBookingData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String bookingId;
    private int ticketQuantity;
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
}
