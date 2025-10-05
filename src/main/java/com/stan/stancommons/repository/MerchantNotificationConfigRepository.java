package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.MerchantNotificationConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantNotificationConfigRepository extends JpaRepository<MerchantNotificationConfig, Long> {
    Optional<MerchantNotificationConfig> findFirstByMerchantId(String  merchantId);
}
