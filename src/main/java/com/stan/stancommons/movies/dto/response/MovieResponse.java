package com.stan.stancommons.movies.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MovieResponse {
    private String code;
    private String message;
    private MovieResponseData data;

    public MovieResponse() {
    }

    public MovieResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
