package com.stan.stancommons.dto.response;

import com.systemspecs.remita.vending.vendingcommon.dto.response.Account;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Data
@Getter
@Setter
@NoArgsConstructor
public class GetBalanceResponse {
    private String message;
    private BigDecimal amount;
    private List<Account> balances = new ArrayList<>();
    private String merchantId;
    private String currency;

    public GetBalanceResponse(BigDecimal amount) {
        this.amount = amount;
    }

    public GetBalanceResponse(String message) {
        this.message = message;
    }
}
