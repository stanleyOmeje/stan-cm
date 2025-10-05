package com.stan.stancommons.service;

import com.systemspecs.remita.vending.vendingcommon.dto.request.AdminProcessorTransactionRequest;
import com.systemspecs.remita.vending.vendingcommon.dto.response.AdminResponse;

public interface ProcessorTransactionService {
    AdminResponse fetchAllTransaction(AdminProcessorTransactionRequest request);
    AdminResponse fetchTransactionByReference(AdminProcessorTransactionRequest request);
}
