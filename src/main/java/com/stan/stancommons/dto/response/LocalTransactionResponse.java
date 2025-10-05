package com.stan.stancommons.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalTransactionResponse {
    private BigDecimal amount;
    private String productCode;
    private String categoryCode;
    private String token;
    private ExtraData extraTokens;
    private String unit;
    private ArrayList<TransactionMetaData> metadata;
    private Date createdAt;
    private Date updatedAt;
    private String clientReference;
    private String paymentIdentifier;
    private int retrialTimeInSec;

}
