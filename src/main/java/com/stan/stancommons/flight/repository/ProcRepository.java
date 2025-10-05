package com.stan.stancommons.flight.repository;

import com.systemspecs.remita.vending.vendingcommon.flight.entity.Processors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProcRepository extends JpaRepository<Processors, Long> {
    Optional<Processors> findByProcessorId(String processorId);
}
