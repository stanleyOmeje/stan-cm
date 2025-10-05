package com.stan.stancommons.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RevendItemsRequest {
    private String paymentIdentifier;
}
