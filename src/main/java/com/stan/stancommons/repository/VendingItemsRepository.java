package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.VendingItems;
import com.systemspecs.remita.vending.vendingcommon.enums.TransactionStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendingItemsRepository extends JpaRepository<VendingItems, Long> {
    List<VendingItems> findAllByVendStatus(TransactionStatus status, PageRequest page);
    List<VendingItems> findAllByBulkClientReference(String bulkClientReference);

    long countByBulkClientReferenceAndVendStatus(String bulkClientReference, TransactionStatus status);

}
