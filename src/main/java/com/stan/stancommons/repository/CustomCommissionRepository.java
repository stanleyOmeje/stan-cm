package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.CustomCommission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomCommissionRepository extends JpaRepository<CustomCommission, Long> {
   Optional<CustomCommission> findFirstByMerchantIdAndProductCode(String merchantId, String productCode);
   Optional<CustomCommission> findFirstById(Long id);
   Optional<CustomCommission> findFirstByMerchantIdAndProductCodeAndProcessor(String merchantId, String productCode,String processor);

   @Query("SELECT c FROM CustomCommission c WHERE c.merchantId = ?1 AND c.productCode = ?2 AND c.processor IS NULL")
   Optional<CustomCommission> findFirstByMerchantIdAndProductCodeAndProcessorIsNull(String merchantId, String productCode);

    Optional<CustomCommission> findFirstByProcessorAndIsPlatformCommissionTrue(String processor);

    List<CustomCommission> findFirstByIsFixedCommissionTrue();

    List<CustomCommission> findFirstByIsFixedCommissionFalse();

    List<CustomCommission> findByMerchantIdAndProductCode(String merchantId, String productCode);

}
