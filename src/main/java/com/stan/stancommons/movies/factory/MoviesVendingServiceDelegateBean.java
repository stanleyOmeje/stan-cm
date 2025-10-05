package com.stan.stancommons.movies.factory;


import com.systemspecs.remita.vending.vendingcommon.movies.service.MoviesProductAbstractVendingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class MoviesVendingServiceDelegateBean {

    private final MoviesVendingServiceFactoryBean moviesVendingServiceFactoryBean;

    public MoviesProductAbstractVendingService getDelegate() {
        return this.moviesVendingServiceFactoryBean.getService();
    }

    public MoviesProductAbstractVendingService getDelegate(String processorId) {
        return this.moviesVendingServiceFactoryBean.getService(processorId);
    }
}
