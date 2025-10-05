package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.VendingServiceRouteConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendingServiceRouteConfigRepository extends JpaRepository<VendingServiceRouteConfig, Long> {
    Optional<VendingServiceRouteConfig> findByProductCodeAndActiveTrue(String productCode);
    Optional<VendingServiceRouteConfig> findByProductCode(String productCode);
    Optional<VendingServiceRouteConfig> findFirstByIgnoreCaseSystemProductTypeAndActiveTrue(String productCode);
    Optional<VendingServiceRouteConfig> findFirstByIgnoreCaseProcessorId(String processorId);
}
