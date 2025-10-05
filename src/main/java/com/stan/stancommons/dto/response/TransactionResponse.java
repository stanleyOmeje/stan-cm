package com.stan.stancommons.dto.response;

import com.systemspecs.remita.vending.vendingcommon.enums.ProductType;
import com.systemspecs.remita.vending.vendingcommon.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private TransactionStatus status;
    private BigDecimal amount;
    private String externalReference;
    private String message;
    private String code;
    private String token;
    private String units;
    private String receiptNumber;
    private String tax;
    private String tariff;
    private int retrialTimeInSec;
    private ExtraData extraData;
    private boolean doFallBack = false;
    private String processorResponseCode;
    private String processorResponseMessage;
    private boolean isReVend;
    private ProductType productType;


    public TransactionResponse(TransactionStatus status) {
        this.status = status;
    }

    public TransactionResponse(TransactionStatus status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public TransactionResponse(TransactionStatus status, String message, boolean doFallBack, String Code) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.doFallBack = doFallBack;
    }

    public TransactionResponse(TransactionStatus status, BigDecimal amount, String externalReference) {
        this.status = status;
        this.amount = amount;
        this.externalReference = externalReference;
    }

    public TransactionResponse(TransactionStatus status, BigDecimal amount, String externalReference, String message) {
        this.status = status;
        this.amount = amount;
        this.externalReference = externalReference;
        this.message = message;
    }

    public TransactionResponse(TransactionStatus status, String externalReference, String message, int retrialTimeInSec) {
        this.status = status;
        this.externalReference = externalReference;
        this.message = message;
        this.retrialTimeInSec = retrialTimeInSec;
    }

    public TransactionResponse(TransactionStatus status, String message, int retrialTimeInSec) {
        this.status = status;
        this.message = message;
        this.retrialTimeInSec = retrialTimeInSec;
    }
}
