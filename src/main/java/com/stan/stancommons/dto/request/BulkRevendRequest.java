package com.stan.stancommons.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BulkRevendRequest {
    private List<RevendItemsRequest> items;
}
