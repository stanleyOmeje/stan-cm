package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.Transaction;
import com.systemspecs.remita.vending.vendingcommon.enums.TransactionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Optional<Transaction> findByProductCode(String code);
    Optional<Transaction> findByUserId(String userId);

    Optional<Transaction> findByInternalReference(String internalReference);

    Optional<Transaction> findByInternalReferenceAndMerchantOrgId(String internalReference, String merchantOrgId);

    Optional<Transaction> findByClientReference(String clientReference);
    Optional<Transaction> findByClientReferenceAndMerchantOrgId(String clientReference,String merchantOrgId);

    @Query("SELECT t FROM Transaction t WHERE t.status IN :statuses")
    List<Transaction> getPendingTransactions(@Param("statuses") List<TransactionStatus> statuses);

    Optional<Transaction> findByInternalReferenceAndUserId(String internalReference, String userId);

    Page<Transaction> findByProductCode(String productCode, Pageable pageable);
   // Optional<Transaction> updatebyStatus(Transaction transaction, String Status);

    @Query("SELECT t FROM Transaction t " +
            "WHERE t.createdAt BETWEEN :startOfDay AND :endOfDay " +
            "AND t.status IN :statuses")
    List<Transaction> getPendingTransactionsForDate(
            @Param("startOfDay") Date startOfDay,
            @Param("endOfDay") Date endOfDay,
            @Param("statuses") List<TransactionStatus> statuses
    );






}

