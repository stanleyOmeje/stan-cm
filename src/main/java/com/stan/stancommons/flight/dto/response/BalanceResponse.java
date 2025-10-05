package com.stan.stancommons.flight.dto.response;

import lombok.Data;

@Data
public class BalanceResponse {
    private String code;
    private String message;
    private BalanceData data;
}
