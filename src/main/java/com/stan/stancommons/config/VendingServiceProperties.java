package com.stan.stancommons.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "vending")
public class VendingServiceProperties {
    private String defaultProcessorId;
    private String fromEmail;
    private boolean sendEmails;
    private boolean sendEmailsWithKafka;
    private Long generalCacheTimeout;
    private List<String> emails;
    ;
}
