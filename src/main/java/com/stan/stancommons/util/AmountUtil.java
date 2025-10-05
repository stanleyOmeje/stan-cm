package com.stan.stancommons.util;

import java.math.BigDecimal;

public class AmountUtil {
    public static boolean invalidMinAmount(BigDecimal minAmount, BigDecimal vendAmount) {
        return minAmount != null && vendAmount != null && vendAmount.compareTo(minAmount) < 0;
    }
}
