package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.ProcessorBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProcessorBalanceRepository extends JpaRepository<ProcessorBalance, Long> {
    Optional<ProcessorBalance> findByProcessorId(String processorId);

    @Query("SELECT DISTINCT p.processorId FROM ProcessorBalance p")
    List<String> findAllProcessorIds();
}
