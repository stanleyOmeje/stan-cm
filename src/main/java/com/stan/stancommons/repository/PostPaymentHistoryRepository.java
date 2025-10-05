package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.PostPaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostPaymentHistoryRepository extends JpaRepository<PostPaymentHistory, Long> {

}
