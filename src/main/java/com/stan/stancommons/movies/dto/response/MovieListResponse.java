package com.stan.stancommons.movies.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieListResponse {
    private List<MovieListItem> data;
    private int page;
    private int size;
    private int total_pages;
    private String responseMessage;
    private String responseCode;

    public MovieListResponse(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
}
