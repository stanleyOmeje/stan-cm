package com.stan.stancommons.repository;


import com.systemspecs.remita.vending.vendingcommon.entity.SmsTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExtendedSmsTemplateRepository extends SmsTemplateRepository {
    Optional<SmsTemplate> findSmsTemplateByTemplateName(String templateName);
}
