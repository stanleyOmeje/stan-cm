package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.DuplicateCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuplicateCheckRepository extends JpaRepository<DuplicateCheck, Long> {
}
