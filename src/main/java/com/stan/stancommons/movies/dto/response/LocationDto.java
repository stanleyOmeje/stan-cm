package com.stan.stancommons.movies.dto.response;

import lombok.Data;

@Data
public class LocationDto {
    private int id;
    private String name;
    private String slug;
    private String address;
    private String state;
    private String country;
    private boolean active;
    private int movie_provider_id;
    private Double web_ticket_charges;
    private Double pos_ticket_charges;
    private Double mobile_ticket_charges;
}
