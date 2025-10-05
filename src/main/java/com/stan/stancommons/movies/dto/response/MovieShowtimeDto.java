package com.stan.stancommons.movies.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class MovieShowtimeDto {
    private int movie_provider_id;
    private String name;
    private String slug;
    private String media_url;
    private String description;
    private String cinema;
    private int production_year;
    private String trailer_url;
    private List<ShowtimeDto> showtimes;
    private List<Object> items;
    private Double web_ticket_charges;
    private Double pos_ticket_charges;
    private Double mobile_ticket_charges;
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


    @Data
    public static class ShowtimeDto {
        private String start_time;
        private String end_time;
        private Double price;
        private String hall;
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
}
