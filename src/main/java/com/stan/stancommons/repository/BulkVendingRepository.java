package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.BulkVending;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BulkVendingRepository extends JpaRepository<BulkVending, Long> {
    Optional<BulkVending> findByClientReference(String clientReference);
}
