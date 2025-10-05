package com.stan.stancommons.dto.response;

import lombok.Data;

@Data
public class AdminResponse {
    private String message;
    private String code;

    private Object data;
}
