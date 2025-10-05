package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.ExtraToken;
import com.systemspecs.remita.vending.vendingcommon.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraTokenRepository extends JpaRepository<ExtraToken, Long> {
    ExtraToken findByTransaction(Transaction transaction);
}
