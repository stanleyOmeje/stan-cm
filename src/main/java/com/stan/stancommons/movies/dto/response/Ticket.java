package com.stan.stancommons.movies.dto.response;

import lombok.Data;

@Data
public class Ticket {
    private String ticket_id;
    private String ticket_name;
    private double amount;
    private Object price_card_id;
    private String vendor_id;
    private double commission_fee;
}
