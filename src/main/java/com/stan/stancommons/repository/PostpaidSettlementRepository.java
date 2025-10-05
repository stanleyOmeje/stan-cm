package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.PostpaidSettlement;
import com.systemspecs.remita.vending.vendingcommon.enums.PostpaidSettlementStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostpaidSettlementRepository extends JpaRepository<PostpaidSettlement, Long> {
    List<PostpaidSettlement> findByStatus(PostpaidSettlementStatus status);
}
