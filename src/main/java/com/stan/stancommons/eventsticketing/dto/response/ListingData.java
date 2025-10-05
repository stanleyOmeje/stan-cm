package com.stan.stancommons.eventsticketing.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ListingData {
    private String listingId;
    private String name;
    private String code;
    private String description;
    private String category;
    private String type;
    private String imageUrl;
}
