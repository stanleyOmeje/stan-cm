package com.stan.stancommons.flight.factory;

import com.systemspecs.remita.vending.vendingcommon.flight.service.FlightProductAbstractVendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class FlightProductVendingServiceFactoryBean extends AbstractFactoryBean<FlightProductAbstractVendingService> implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    private static final String PROCESSOR_SERVICE = "%sProcessorService";

    @Qualifier("flightProductVendingServiceFactoryBean")
    private FlightProductAbstractVendingService flightProductVendingService;


    @Override
    public Class<?> getObjectType() {
        return this.flightProductVendingService != null ? this.flightProductVendingService.getClass() : null;
    }


    @Override
    protected FlightProductAbstractVendingService createInstance() throws Exception {
        return this.flightProductVendingService;
    }


    public FlightProductAbstractVendingService getService() {
        try {
            return getObject();
        } catch (Exception e) {
        }
        return null;
    }


    public FlightProductAbstractVendingService getService(String processorId) {
        if (processorId == null) {
            return this.flightProductVendingService;
        }
        String beanName = String.format(PROCESSOR_SERVICE, processorId.toLowerCase());
        System.out.println(this.applicationContext.containsBean(beanName));
        if
        (applicationContext != null && this.applicationContext.containsBean(beanName)) {
            this.flightProductVendingService = (FlightProductAbstractVendingService) this.applicationContext.getBean(beanName);

        }
        return this.flightProductVendingService;
    }


    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        System.out.println("setting context");
        this.applicationContext = applicationContext;
    }
}
