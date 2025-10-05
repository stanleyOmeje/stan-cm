package com.stan.stancommons.service;

import lombok.Data;
import org.springframework.context.ApplicationEvent;


@Data
public class VendingServiceRegisteredEvent extends ApplicationEvent {

    private static final long serialVersionUID = -4145612126270968742L;

    private String processorId;

    public VendingServiceRegisteredEvent(Object source) {
        super(source);
    }
}
