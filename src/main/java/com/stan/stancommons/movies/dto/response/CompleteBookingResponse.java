package com.stan.stancommons.movies.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteBookingResponse {
    private String message;
    private Boolean status;
}
