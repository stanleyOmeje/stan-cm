package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.FeeMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeeMappingRepository extends JpaRepository<FeeMapping, Long> {
    Optional<FeeMapping> findFirstByProductCode(String productCode);
}
