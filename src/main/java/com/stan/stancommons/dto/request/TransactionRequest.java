package com.stan.stancommons.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    @NotBlank(message = "product code is require")
    private String productCode;

    private BigDecimal amount;

    private String clientReference;

    private String paymentIdentifier;

    @NotNull(message = "transaction data is required")
    private TransactionDataRequest data;
    private String profileId;
    private boolean isBulkVending = false;
    private String internalReference;
    private boolean reVend;
    private String caller;
    private boolean submittedForReversals = false;
}
