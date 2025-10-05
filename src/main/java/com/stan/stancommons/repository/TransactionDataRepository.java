package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.Transaction;
import com.systemspecs.remita.vending.vendingcommon.entity.TransactionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionDataRepository extends JpaRepository<TransactionData,Long> {
    Optional<TransactionData> findByTransaction(Transaction transaction);
}
