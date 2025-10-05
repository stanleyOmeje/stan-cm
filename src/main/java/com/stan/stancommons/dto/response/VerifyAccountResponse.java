package com.stan.stancommons.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.systemspecs.remita.vending.vendingcommon.enums.VerificationStatus;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerifyAccountResponse {

    private String message;

    private VerificationStatus status;

    private VerificationData data;

    public VerifyAccountResponse(String message, VerificationStatus status) {
        this.message = message;
        this.status = status;
    }
}
