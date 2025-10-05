package com.stan.stancommons.dto.request;

import lombok.Data;

@Data
public class AdminUniqueRequest {
    private String processorId;
    private long vendorId;
    private String userCode;
    private String receipt;
    private String accountNumber;
    private String account;
    private long dateFrom;
    private long dateTo;
    private String shiftUserCode;
    private long paymentDate;
}
