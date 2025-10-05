package com.stan.stancommons.eventsticketing.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CategoryResponse {
    private String code;
    private String message;
    private List<CategoryResponseData> data;

    public CategoryResponse() {
    }

    public CategoryResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
