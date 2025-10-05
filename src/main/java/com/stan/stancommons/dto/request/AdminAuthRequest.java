package com.stan.stancommons.dto.request;

import lombok.Data;

@Data
public class AdminAuthRequest {
    private String processorId;
    private long vendorId;
    private String userCode;
    private String currentPwd;
    private String newPwd;

}
