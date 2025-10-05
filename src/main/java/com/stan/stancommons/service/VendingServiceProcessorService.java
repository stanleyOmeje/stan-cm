package com.stan.stancommons.service;

import com.systemspecs.remita.extended.utils.RedisUtility;
import com.systemspecs.remita.vending.vendingcommon.config.VendingServiceProperties;
import com.systemspecs.remita.vending.vendingcommon.entity.VendingServiceRouteConfig;
import com.systemspecs.remita.vending.vendingcommon.repository.VendingServiceRouteConfigRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VendingServiceProcessorService {

    private final VendingServiceProperties properties;
    private final VendingServiceRouteConfigRepository configRepository;
    private final RedisUtility redisUtility;


    public String getProcessorId(String productCode) {
        StopWatch stopWatch = new StopWatch("getProcessorId");
        log.info(">>> [getProcessorId] Start - Looking up processorId for productCode: {}", productCode);

        String cacheKey = "PROCESSOR_ID:" + productCode;
        String processorId = null;

        try {
            try {
                stopWatch.start("redisLookup");
                processorId = redisUtility.getObjectFromRedis(cacheKey, String.class);
                stopWatch.stop();
            } catch (Exception redisEx) {
                stopWatch.stop();
                log.warn("[getProcessorId] Redis lookup failed for productCode={} | Reason={}", productCode, redisEx.getMessage());
            }

            if (processorId != null) {
                log.info("[getProcessorId] ProcessorId for productCode={} found in Redis: {}", productCode, processorId);
                log.info("[getProcessorId] Timings: {}", stopWatch.prettyPrint());
                return processorId;
            }

            stopWatch.start("dbLookup");
            Optional<VendingServiceRouteConfig> routeConfig =
                    configRepository.findByProductCodeAndActiveTrue(productCode);
            stopWatch.stop();

            if (routeConfig.isPresent()) {
                processorId = routeConfig.get().getProcessorId();
                log.info("[getProcessorId] ProcessorId for productCode={} loaded from DB: {}", productCode, processorId);

                try {
                    stopWatch.start("redisSave");
                    redisUtility.saveObjectToRedis(cacheKey, processorId, 60);
                    stopWatch.stop();
                    log.info("[getProcessorId] ProcessorId for productCode={} cached in Redis for 60s", productCode);
                } catch (Exception redisEx) {
                    stopWatch.stop();
                    log.warn("[getProcessorId] Failed to cache processorId in Redis for productCode={} | Reason={}", productCode, redisEx.getMessage());
                }

                return processorId;
            }

            log.warn("[getProcessorId] No active routeConfig found for productCode={}", productCode);
            return null;

        } finally {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            log.info("[getProcessorId] Completed for productCode={} | Timings: {}", productCode, stopWatch.prettyPrint());
        }
    }



    public String getFallbackProcessorId(String productCode) {
        log.info("inside getFallbackProcessorId with productCode ...{}", productCode);

        String cacheKey = "FALLBACK_PROCESSOR_ID:" + productCode;

        String processorId = redisUtility.getObjectFromRedis(cacheKey, String.class);
        if (processorId != null) {
            log.info("Fallback ProcessorId for productCode {} found in Redis: {}", productCode, processorId);
            return processorId;
        }

        Optional<VendingServiceRouteConfig> routeConfig =
                configRepository.findByProductCodeAndActiveTrue(productCode);

        log.info("routeConfig...{}", routeConfig);

        if (routeConfig.isPresent() && routeConfig.get().isEnableFallBackProcessor()) {
            processorId = routeConfig.get().getFallBackProcessorId();
            log.info("Profile ID found: configuring fallback processorId {}", processorId);

            redisUtility.saveObjectToRedis(cacheKey, processorId, 60);

            return processorId;
        }

        log.info("Profile ID NOT found or fallback not enabled, unable to configure processorId");
        return null;
    }



}
