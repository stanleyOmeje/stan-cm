package com.stan.stancommons.factory;

import com.systemspecs.remita.vending.vendingcommon.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class VendingServiceDelegateBean {

    @Autowired
    private VendingServiceFactoryBean vendingServiceFactoryBean;


    public AbstractVendingService getDelegate() {
        return this.vendingServiceFactoryBean.getService();
    }


    public AbstractVendingService getDelegate(String processorId) {return this.vendingServiceFactoryBean.getService(processorId);
    }

    public AuthPortalService getAuthDelegate(String processorId) {
        return this.vendingServiceFactoryBean.getAuthService(processorId);
    }

    public UserManagementService getUserDelegate(String processorId) {
        return this.vendingServiceFactoryBean.getUserService(processorId);
    }

    public ProcessorTransactionService getProcessorTransactionDelegate(String processorId) {
        return this.vendingServiceFactoryBean.getTransactionServiceForProcessor(processorId);
    }
    public ProcessorUniqueService getProcessorUniqueDelegate(String processorId) {
        return this.vendingServiceFactoryBean.getProcessorUniqueService(processorId);

    }

}
