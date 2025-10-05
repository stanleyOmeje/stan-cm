package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.ProcessorPackages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessorPackagesRepository extends JpaRepository<ProcessorPackages, Long> {
    Optional<ProcessorPackages> findFirstByProductCodeAndProcessorId(String productCode, String processorId);

    List<ProcessorPackages> findByProcessorId(String processorId);
    Optional<ProcessorPackages> findByProductCode(String productCode);
}
