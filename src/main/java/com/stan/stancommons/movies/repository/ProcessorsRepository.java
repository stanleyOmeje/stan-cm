package com.stan.stancommons.movies.repository;


import com.systemspecs.remita.vending.vendingcommon.flight.entity.Processors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcessorsRepository extends JpaRepository<Processors, Long> {
    Optional<Processors> findByProcessorId(String processorId);
}
