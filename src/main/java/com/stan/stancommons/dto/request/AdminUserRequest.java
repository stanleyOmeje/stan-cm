package com.stan.stancommons.dto.request;

import lombok.Data;

@Data
public class AdminUserRequest {
    private String processorId;
    private long vendorId;
    private String userCode;
    private String name;
    private String newUserCode;
    private String email;
    private boolean userAdmin;
    private boolean active;
    private String phoneNo;
    private String modUserCode;
    private String newName;
    private String newEmail;
    private boolean newIsActive;
}
