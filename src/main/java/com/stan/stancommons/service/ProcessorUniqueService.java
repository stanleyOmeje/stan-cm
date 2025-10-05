package com.stan.stancommons.service;

import com.systemspecs.remita.vending.vendingcommon.dto.request.AdminUniqueRequest;
import com.systemspecs.remita.vending.vendingcommon.dto.response.AdminResponse;

public interface ProcessorUniqueService {
    AdminResponse customerEnquiry(AdminUniqueRequest adminUniqueRequest);
    AdminResponse shiftEnquiry(AdminUniqueRequest adminUniqueRequest);
    AdminResponse vendorInformation(AdminUniqueRequest adminUniqueRequest);
    AdminResponse criteriaType(AdminUniqueRequest adminUniqueRequest);
}
