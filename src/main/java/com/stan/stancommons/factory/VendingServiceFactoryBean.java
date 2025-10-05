package com.stan.stancommons.factory;

import com.systemspecs.remita.vending.vendingcommon.service.*;
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
public class VendingServiceFactoryBean extends AbstractFactoryBean<AbstractVendingService> implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    private static final String PROCESSOR_SERVICE = "%sProcessorService";

    @Qualifier("flightProductVendingServiceFactoryBean")
    private AbstractVendingService vendingService;


    @Override
    public Class<?> getObjectType() {
        return this.vendingService != null ? this.vendingService.getClass() : null;
    }


    @Override
    protected AbstractVendingService createInstance() throws Exception {
        return this.vendingService;
    }


    public AbstractVendingService getService() {
        try {
            return getObject();
        } catch (Exception e) {
        }
        return null;
    }


    public AbstractVendingService getService(String processorId) {
        if (processorId == null) {
            return this.vendingService;
        }
        String beanName = String.format(PROCESSOR_SERVICE, processorId.toLowerCase());
        System.out.println(this.applicationContext.containsBean(beanName));
        if
        (applicationContext != null && this.applicationContext.containsBean(beanName)) {
            this.vendingService = (AbstractVendingService) this.applicationContext.getBean(beanName);

        }
        return this.vendingService;
    }

    public AuthPortalService getAuthService(String processorId) {
        if (processorId == null) {
        }
        String beanName = String.format(PROCESSOR_SERVICE, processorId.toLowerCase());
        System.out.println(this.applicationContext.containsBean(beanName));
        if (applicationContext != null && this.applicationContext.containsBean(beanName)) {
            try {
                return (AuthPortalService) this.applicationContext.getBean(beanName);
            } catch (Exception e) {
                log.info("Error getting auth portal, Module may not be a member of AuthPorter set...{}", e.getMessage());
            }

        }
        return null;
    }

    public ProcessorUniqueService getProcessorUniqueService(String processorId) {
        if (processorId == null) {
        }
        String beanName = String.format("%sProcessorService", processorId.toLowerCase());
        System.out.println(this.applicationContext.containsBean(beanName));
        if (applicationContext != null && this.applicationContext.containsBean(beanName)) {
            try {
                return (ProcessorUniqueService) this.applicationContext.getBean(beanName);
            } catch (Exception e) {
                log.info("Error getting auth portal, Module may not be a member of AuthPorter set...{}", e.getMessage());
            }

        }
        return null;
    }


    public UserManagementService getUserService(String processorId) {
        if (processorId == null || processorId.isEmpty()) {
            throw new IllegalArgumentException("Processor ID cannot be null or empty.");
        }

        String beanName = String.format(PROCESSOR_SERVICE, processorId.toLowerCase());
        boolean beanExists = false;

        if (this.applicationContext != null) {
            beanExists = this.applicationContext.containsBean(beanName);
        }

        if (beanExists) {
            try {
                return this.applicationContext.getBean(beanName, UserManagementService.class);
            } catch (BeansException e) {
                log.error("Error getting user management for processor ID '{}': {}", processorId, e.getMessage());
            }
        } else {
            log.warn("User management bean not found for processor ID '{}'", processorId);
        }

        return null;
    }


    public ProcessorTransactionService getTransactionServiceForProcessor(String processorId) {
        if (processorId == null || processorId.isEmpty()) {
            throw new IllegalArgumentException("Processor ID is null or empty.");
        }

        String beanName = String.format(PROCESSOR_SERVICE, processorId.toLowerCase());
        boolean beanExists = false;

        if (this.applicationContext != null) {
            beanExists = this.applicationContext.containsBean(beanName);
        }

        if (beanExists) {
            try {
                return this.applicationContext.getBean(beanName, ProcessorTransactionService.class);
            } catch (BeansException e) {
                log.error("Error fetching transactions for processor ID '{}': {}", processorId, e.getMessage());
            }
        } else {
            log.warn("Processor Transaction service bean not found for processor ID '{}'", processorId);
        }

        return null;
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
