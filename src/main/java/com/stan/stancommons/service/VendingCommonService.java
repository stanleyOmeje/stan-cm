package com.stan.stancommons.service;

import com.systemspecs.remita.vending.vendingcommon.dto.request.ProcessorPackageRequest;
import com.systemspecs.remita.vending.vendingcommon.dto.response.TransactionResponse;
import com.systemspecs.remita.vending.vendingcommon.entity.Transaction;

public interface VendingCommonService {

    TransactionResponse queryTransaction(Transaction transaction);

    Object getVendingProcessorPackage();

    Object createVendingProcessorPackage(ProcessorPackageRequest vendingProcessorPackageRequest);

}
