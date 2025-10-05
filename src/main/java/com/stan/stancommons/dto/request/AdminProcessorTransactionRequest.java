package com.stan.stancommons.dto.request;

import lombok.Data;
@Data
public class AdminProcessorTransactionRequest {
    private String processorId;
    private long vendorId;
    private String password;
    private double dateFrom;
    private double dateTo;
}
