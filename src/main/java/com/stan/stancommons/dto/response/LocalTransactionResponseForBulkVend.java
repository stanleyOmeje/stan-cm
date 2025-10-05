package com.stan.stancommons.dto.response;

import com.systemspecs.remita.vending.vendingcommon.enums.TransactionStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
@Data
public class LocalTransactionResponseForBulkVend {
    private BigDecimal amount;
    private TransactionStatus status;
    private String responseMessage;
    private String productCode;
    private String categoryCode;
    private String token;
    private ExtraData extraTokens;
    private String unit;
    private ArrayList<TransactionMetaData> metadata;
    private Date createdAt;
    private Date updatedAt;
    private String reference;
    private int retrialTimeInSec;
}
