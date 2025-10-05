package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.ResponseCodeMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseCodeMappingRepository extends JpaRepository<ResponseCodeMapping, Long> {
    List<ResponseCodeMapping> findByProcessorIdAndProcessorResponseCode(String processorId, String processorResponseCode);
    List<ResponseCodeMapping>findByProcessorIdAndProcessorResponseCodeAndProcessorResponseMessage(String processorId, String processorResponseCode, String processorResponseMessage);
}
