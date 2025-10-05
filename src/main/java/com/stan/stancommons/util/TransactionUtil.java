package com.stan.stancommons.util;

import com.systemspecs.remita.vending.vendingcommon.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TransactionUtil {

    private final TransactionRepository transactionRepository;

}
