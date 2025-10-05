package com.stan.stancommons.util;


import org.springframework.stereotype.Component;

@Component
public class IdGenerator {
    public String generateId(){
        return String.valueOf(System.nanoTime());
    }
}
