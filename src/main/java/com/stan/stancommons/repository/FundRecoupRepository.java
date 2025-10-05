package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.FundRecoup;
import com.systemspecs.remita.vending.vendingcommon.enums.DebitStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FundRecoupRepository extends JpaRepository<FundRecoup, Long> {
    Optional<FundRecoup> findByReference(String reference);
    List<FundRecoup> findByStatus(DebitStatus status);
}
