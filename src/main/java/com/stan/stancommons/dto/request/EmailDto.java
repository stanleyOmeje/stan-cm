package com.stan.stancommons.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
    private String fromEmail;
    private String toEmail;
    private String processorName;
    private BigDecimal accountBalance;
    private BigDecimal minimumBalance;
}
