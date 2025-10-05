package com.stan.stancommons.movies.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateMovieBokingTicket {
    private Long ticketId;
    private String ticketName;
    private Integer quantity;
    private BigDecimal price;
}
