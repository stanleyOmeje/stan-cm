package com.stan.stancommons.dto.request;

import lombok.Data;

@Data
public class ProviderSearchCriteria {
    private String code;
    private String name;
    private String description;
}
