package com.stan.stancommons.dto.request;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetadataRequest {
    private String field;

    private String value;
}
