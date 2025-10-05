package com.stan.stancommons.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDataRequest {
    @NotBlank(message = "account number is required")
    private String accountNumber;

    private String phoneNumber;
}
