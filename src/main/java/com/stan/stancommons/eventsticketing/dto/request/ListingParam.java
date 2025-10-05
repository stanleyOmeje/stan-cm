package com.stan.stancommons.eventsticketing.dto.request;

import lombok.Data;

@Data
public class ListingParam {
    int page;
    int pageSize;
    String search;
    String categoryId;
}
