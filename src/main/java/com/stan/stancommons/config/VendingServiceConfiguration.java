package com.stan.stancommons.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.systemspecs.remita.vending.vendingcommon.eventsticketing.repository",
    "com.systemspecs.remita.vending.vendingcommon.movies.repository","com.systemspecs.remita.vending.vendingcommon.flight.repository"})
@EntityScan(basePackages = {"com.systemspecs.remita.vending.vendingcommon"})
@EnableConfigurationProperties(VendingServiceProperties.class)
@Configuration
public class VendingServiceConfiguration {

}
