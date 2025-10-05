package com.stan.stancommons.eventsticketing.factory;

import com.systemspecs.remita.vending.vendingcommon.eventsticketing.service.EventProductAbstractVendingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class EventProductVendingServiceDelegateBean {

    @Autowired
    private EventProductVendingServiceFactoryBean eventProductVendingServiceFactoryBean;


    public EventProductAbstractVendingService getDelegate() {
        return this.eventProductVendingServiceFactoryBean.getService();
    }


    public EventProductAbstractVendingService getDelegate(String processorId) {return this.eventProductVendingServiceFactoryBean.getService(processorId);
    }

}
