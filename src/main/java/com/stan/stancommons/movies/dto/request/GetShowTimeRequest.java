package com.stan.stancommons.movies.dto.request;

import lombok.Data;

@Data
public class GetShowTimeRequest {
    private String movieName;
    private String cinemaName;
    private String productCode;
}
