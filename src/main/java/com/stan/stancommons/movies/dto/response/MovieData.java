package com.stan.stancommons.movies.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class MovieData {
    private int movie_provider_id;
    private String name;
    private String slug;
    private String media_url;
    private String description;
    private String cinema;
    private int production_year;
    private String trailer_url;
    private List<Showtime> showtimes;
    private List<Object> items;
    private Object web_ticket_charges;
    private Object pos_ticket_charges;
    private Object mobile_ticket_charges;
    private String backdrop_url;
    private String poster_url;
    private String release_date;
    private Object adult;
    private double vote_average;
    private Object vote_count;
    private String original_language;
    private List<Object> genres;
    private double budget;
    private double revenue;
    private Object runtime;
    private Object production_status;
    private Object tagline;
    private Object homepage;
    private List<Object> production_countries;
    private List<Object> spoken_languages;
}
