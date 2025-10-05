package com.stan.stancommons.eventsticketing.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TicketInfo {
    private String ticketId;
    private String name;
    private String description;
    private BigDecimal amount;
    private int admits;
    private int quantityAvailable;
    private String usagePeriod;
    private boolean active;
    private BigDecimal fee;
}
