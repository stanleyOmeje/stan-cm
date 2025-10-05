package com.stan.stancommons.movies.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MovieResponseData {
    private Integer totalPage;
    private Integer totalContent;
    private List<MovieListItem> items;
}
