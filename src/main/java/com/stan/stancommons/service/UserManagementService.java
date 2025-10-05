package com.stan.stancommons.service;
import com.systemspecs.remita.vending.vendingcommon.dto.request.AdminUserRequest;
import com.systemspecs.remita.vending.vendingcommon.dto.response.AdminResponse;

public interface UserManagementService {

    AdminResponse createUser(AdminUserRequest request);

    AdminResponse modifyUser(AdminUserRequest request);
    AdminResponse searchUser(AdminUserRequest request);

}
