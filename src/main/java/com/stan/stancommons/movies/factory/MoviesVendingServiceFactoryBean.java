package com.stan.stancommons.movies.factory;

import com.systemspecs.remita.vending.vendingcommon.movies.service.MoviesProductAbstractVendingService;
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
public class MoviesVendingServiceFactoryBean extends AbstractFactoryBean<MoviesProductAbstractVendingService> implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    private static final String PROCESSOR_SERVICE = "%sProcessorService";

    @Qualifier("moviesVendingServiceFactoryBean")
    private MoviesProductAbstractVendingService moviesVendingService;

    @Override
    public Class<?> getObjectType() {
        return this.moviesVendingService != null ? this.moviesVendingService.getClass() : null;
    }

    @Override
    protected MoviesProductAbstractVendingService createInstance() throws Exception {
        return this.moviesVendingService;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setting context");
        this.applicationContext = applicationContext;
    }

    public MoviesProductAbstractVendingService getService() {
        try {
            return getObject();
        } catch (Exception e) {
            log.info("Error getting movie vending service", e);
        }
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public MoviesProductAbstractVendingService getService(String processorId) {
        if (processorId == null) {
            return this.moviesVendingService;
        }
        String beanName = String.format(PROCESSOR_SERVICE, processorId.toLowerCase());
        System.out.println(this.applicationContext.containsBean(beanName));
        if
        (applicationContext != null && this.applicationContext.containsBean(beanName)) {
            this.moviesVendingService = (MoviesProductAbstractVendingService) this.applicationContext.getBean(beanName);

        }
        return this.moviesVendingService;
    }
}
