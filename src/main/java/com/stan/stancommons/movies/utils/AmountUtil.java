package com.stan.stancommons.movies.utils;

import java.math.BigDecimal;

public class AmountUtil {
    public static Boolean validateAmount(BigDecimal amount){
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }
}
