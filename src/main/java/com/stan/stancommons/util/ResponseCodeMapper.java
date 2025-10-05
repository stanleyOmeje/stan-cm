package com.stan.stancommons.util;

import com.systemspecs.remita.vending.vendingcommon.entity.ResponseCodeMapping;
import com.systemspecs.remita.vending.vendingcommon.enums.TransactionStatus;
import com.systemspecs.remita.vending.vendingcommon.repository.ResponseCodeMappingRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@RequiredArgsConstructor
@Component
@Slf4j
public class ResponseCodeMapper {

    private final ResponseCodeMappingRepository repository;

    public String mapProcessorCodeToStandardCode(String responseCode, String processorId) {
        List<ResponseCodeMapping> responseCodeMapping = repository.findByProcessorIdAndProcessorResponseCode(processorId, responseCode);
        if (!responseCodeMapping.isEmpty()) {
            return responseCodeMapping.get(0).getStandardResponseCode();
        }
        return TransactionStatus.UNKNOWN_CODE.getCode();
    }

    public String mapProcessorCodeToStandardCode(String responseCode, String processorId, String responseMessage) {
        log.info("Over loaded method to accommodate VTPass processor Id");
        List<ResponseCodeMapping> responseCodeMapping = repository.findByProcessorIdAndProcessorResponseCodeAndProcessorResponseMessage(processorId, responseCode, responseMessage);
        if (!responseCodeMapping.isEmpty()) {
            return responseCodeMapping.get(0).getStandardResponseCode();
        }
        return TransactionStatus.UNKNOWN_CODE.getCode();
    }

    public String mapProcessorMessageToStandardMessage(String responseCode, String processorId) {
        List<ResponseCodeMapping> responseCodeMapping = repository.findByProcessorIdAndProcessorResponseCode(processorId, responseCode);
        if (!responseCodeMapping.isEmpty()) {
            if(StringUtil.isNotBlank(responseCodeMapping.get(0).getCustomMessage())){
                return responseCodeMapping.get(0).getCustomMessage();
            }
            return responseCodeMapping.get(0).getStandardResponseMessage();
        }
        return TransactionStatus.UNKNOWN_CODE.getMessage();
    }

    public String mapProcessorMessageToStandardMessage(String responseCode, String processorId, String responseMessage) {
        log.info("Over loaded method to accommodate VTPass processor Id");
        List<ResponseCodeMapping> responseCodeMapping = repository.findByProcessorIdAndProcessorResponseCodeAndProcessorResponseMessage(processorId, responseCode, responseMessage);
        if (!responseCodeMapping.isEmpty()) {
            if(StringUtil.isNotBlank(responseCodeMapping.get(0).getCustomMessage())){
                return responseCodeMapping.get(0).getCustomMessage();
            }
            return responseCodeMapping.get(0).getStandardResponseMessage();
        }
        return TransactionStatus.UNKNOWN_CODE.getMessage();
    }
}

