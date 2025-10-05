package com.stan.stancommons.flight.factory;

import com.systemspecs.remita.vending.vendingcommon.flight.service.FlightProductAbstractVendingService;
import com.systemspecs.remita.vending.vendingcommon.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class FlightProductVendingServiceDelegateBean {

    @Autowired
    private FlightProductVendingServiceFactoryBean flightProductVendingServiceFactoryBean;


    public FlightProductAbstractVendingService getDelegate() {
        return this.flightProductVendingServiceFactoryBean.getService();
    }


    public FlightProductAbstractVendingService getDelegate(String processorId) {return this.flightProductVendingServiceFactoryBean.getService(processorId);
    }

}
