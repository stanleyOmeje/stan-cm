package com.stan.stancommons.movies.dto.response;

import lombok.Data;

@Data
public class MovieListItem {
    private String name;
    private String slug;
    private String description;
    private String category;
    private String listingType;
    private String imageUrl;


}
