package com.stan.stancommons.eventsticketing.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LocationBookingResponseData {
    private int id;
    private int ticket_quantity;
    private int ticket_amount;
    private int discount_amount;
    private int amount_due;
    private boolean is_free;
    private String product_reference;
    private String booking_ref;
    private String session;
    private String payment_ref;
    private Date created_at;
    private Date expires_at;
}
