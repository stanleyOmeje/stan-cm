package com.stan.stancommons.eventsticketing.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class Attendees {
    private String name;
    private String email;
    private String phone;
    private Date startDate;
}
