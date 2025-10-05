package com.stan.stancommons.eventsticketing.factory;

import com.systemspecs.remita.vending.vendingcommon.eventsticketing.service.EventProductAbstractVendingService;
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
public class EventProductVendingServiceFactoryBean extends AbstractFactoryBean<EventProductAbstractVendingService> implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    private static final String PROCESSOR_SERVICE = "%sProcessorService";

    @Qualifier("eventProductVendingServiceFactoryBean")
    private EventProductAbstractVendingService eventProductVendingService;


    @Override
    public Class<?> getObjectType() {
        return this.eventProductVendingService != null ? this.eventProductVendingService.getClass() : null;
    }


    @Override
    protected EventProductAbstractVendingService createInstance() throws Exception {
        return this.eventProductVendingService;
    }


    public EventProductAbstractVendingService getService() {
        try {
            return getObject();
        } catch (Exception e) {
        }
        return null;
    }


    public EventProductAbstractVendingService getService(String processorId) {
        log.info("Inside EventProductVendingServiceFactoryBean:: getService for processorId {}", processorId);
        if (processorId == null) {
            return this.eventProductVendingService;
        }
        String beanName = String.format(PROCESSOR_SERVICE, processorId.toLowerCase());
        System.out.println(this.applicationContext.containsBean(beanName));
        if
        (applicationContext != null && this.applicationContext.containsBean(beanName)) {
            this.eventProductVendingService = (EventProductAbstractVendingService) this.applicationContext.getBean(beanName);

        }
        log.info("eventProductVendingService...{}", this.eventProductVendingService);
        return this.eventProductVendingService;
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
