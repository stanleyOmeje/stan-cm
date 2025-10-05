package com.stan.stancommons.eventsticketing.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SingleListingData {
    private String listingId;
    private String name;
    private String code;
    private String description;
    private String category;
    private String type;
    private String imageUrl;
    private List<TicketInfo> tickets;
}
