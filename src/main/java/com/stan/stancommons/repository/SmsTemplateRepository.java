package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.SmsTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SmsTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SmsTemplateRepository extends JpaRepository<SmsTemplate, Long>, JpaSpecificationExecutor<SmsTemplate> {}
