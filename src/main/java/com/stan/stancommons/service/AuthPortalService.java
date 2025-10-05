package com.stan.stancommons.service;

import com.systemspecs.remita.vending.vendingcommon.dto.request.AdminAuthRequest;
import com.systemspecs.remita.vending.vendingcommon.dto.response.AdminResponse;
public interface AuthPortalService {

    AdminResponse getLogin(AdminAuthRequest adminAuthRequest);
    AdminResponse validatePassword(AdminAuthRequest adminAuthRequest);
    AdminResponse changePassword(AdminAuthRequest adminAuthRequest);
    AdminResponse forgotPassword(AdminAuthRequest adminAuthRequest);
}
