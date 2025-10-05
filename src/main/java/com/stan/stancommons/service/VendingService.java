package com.stan.stancommons.service;

import com.systemspecs.remita.vending.vendingcommon.dto.VerifyRequest;
import com.systemspecs.remita.vending.vendingcommon.dto.request.TransactionRequest;
import com.systemspecs.remita.vending.vendingcommon.dto.response.*;

public interface VendingService {

    VerifyAccountResponse verifyAccount(String uniqueIdentifier, String productCode);

    GetBalanceResponse getBalance();

    TransactionResponse initiateTransaction(TransactionRequest request, String transmittedId);

    default GetServiceResponse getServices() {
        return new GetServiceResponse();
    }

    default PriceResponse getCurrentPrice(String productCode) {
        return new PriceResponse();
    }

    default VerifyAccountResponse verifyAccount(VerifyRequest request) {
        return null;
    }

}
