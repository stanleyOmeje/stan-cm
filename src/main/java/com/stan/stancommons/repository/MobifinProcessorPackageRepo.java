package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.MobifinProcessorPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MobifinProcessorPackageRepo extends JpaRepository<MobifinProcessorPackage, Long> {
    Optional<MobifinProcessorPackage> findFirstByProductCode(String productCode);
}
