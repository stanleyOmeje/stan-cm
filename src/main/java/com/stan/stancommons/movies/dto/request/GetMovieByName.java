package com.stan.stancommons.movies.dto.request;

import lombok.Data;

@Data
public class GetMovieByName {
    private String name;
    private String productCode;
}
