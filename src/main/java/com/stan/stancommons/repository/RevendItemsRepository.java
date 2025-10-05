package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.RevendItems;
import com.systemspecs.remita.vending.vendingcommon.enums.TransactionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RevendItemsRepository extends JpaRepository<RevendItems, Long> {
    List<RevendItems> findAllByVendStatus(TransactionStatus status, PageRequest page);
    Page<RevendItems> findAllByBulkRevendReferenceId(String bulkRevendReferenceId, Pageable pageable);
}
