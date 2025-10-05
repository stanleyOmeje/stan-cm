package com.stan.stancommons.movies.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieShowtimeResponse {
    private MovieData data;
    private String code;
    private String message;
    private boolean status;

    public MovieShowtimeResponse(String message, boolean b) {
        this.message = message;
        this.status = b;
    }
}
