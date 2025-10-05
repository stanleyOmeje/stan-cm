package com.stan.stancommons.movies.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ShowtimeDto {
    private String date;
    private List<SlotDto> slots;
}
