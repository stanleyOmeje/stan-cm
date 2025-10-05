package com.stan.stancommons.movies.dto.request;

import lombok.Data;

@Data
public class MovieParam {
    int page;
    int pageSize;
    String search;
    String categoryId;
}
