package com.stan.stancommons.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueryTransactionResponse {
    private BigDecimal amount;
    private String productCode;
    private String categoryCode;
    private String token;
    private QueryExtraData extraTokens;
    private String unit;
    private Date createdAt;
    private String clientReference;
    private String paymentIdentifier;

    private String accountNumber;
    private String phoneNumber;
    private String responseMessage;
    private String transactionStatus;

}
