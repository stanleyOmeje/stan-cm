package com.stan.stancommons.repository;
import com.systemspecs.remita.vending.vendingcommon.entity.GloProcessorPackages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GloProcessorPackagesRepository extends JpaRepository<GloProcessorPackages, Long> {
    Optional<GloProcessorPackages> findFirstByProductCode(String productCode);
}
