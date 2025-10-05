package com.stan.stancommons.eventsticketing.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ListingResponseData {
    private Integer totalPage;
    private Integer totalContent;
    private List<ListingData> items;

}
