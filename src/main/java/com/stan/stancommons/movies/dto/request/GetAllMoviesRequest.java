package com.stan.stancommons.movies.dto.request;

import lombok.Data;

@Data
public class GetAllMoviesRequest {
    private int page;
    private int size;
    private String productCode;
}
