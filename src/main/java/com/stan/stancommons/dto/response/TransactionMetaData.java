package com.stan.stancommons.dto.response;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class TransactionMetaData {
    @NotBlank(message = "account number is required")
    private String accountNumber;
    private String phoneNumber;
}
