package com.stan.stancommons.movies.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleMoviesResponseDto {
    private MovieData data;
    private String message;
    private String code;
    private boolean status;

    @Data
    public static class MovieData {
        private int id;
        private String name;
        private String slug;
        private String reference;
        private String description;
        private String media;
        private Map<String, List<Location>> locations; // "lagos" -> cinemas
        private String backdrop_url;
        private String poster_url;
        private String release_date;
        private Boolean adult;
        private double vote_average;
        private Integer vote_count;
        private String original_language;

        private List<Genre> genres;
        private double budget;
        private double revenue;
        private Integer runtime;
        private String production_status;
        private String tagline;
        private String homepage;
        private List<ProductionCountry> production_countries;
        private List<SpokenLanguage> spoken_languages;
    }

    @Data
    public static class Location {
        private int id;
        private String name;
        private String slug;
        private String address;
        private String state;
        private String country;
        private boolean active;
        private int movie_provider_id;
        private String web_ticket_charges;
        private String pos_ticket_charges;
        private String mobile_ticket_charges;
    }

    @Data
    public static class Genre {
        private int id;
        private String name;
    }

    @Data
    public static class ProductionCountry {
        private String iso_3166_1;
        private String name;
    }

    @Data
    public static class SpokenLanguage {
        private String english_name;
        private String iso_639_1;
        private String name;
    }

    public SingleMoviesResponseDto(String message, boolean b) {
        this.message = message;
        this.status = b;
    }

}
