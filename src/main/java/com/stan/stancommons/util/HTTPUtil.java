package com.stan.stancommons.util;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HTTPUtil {

    private final RestTemplateUtility restTemplateUtility;

    public String sendPostRequest(Object data, String url, HttpHeaders headers) {

        try {
            HttpEntity<Object> entity = new HttpEntity<>(data, headers);
            ResponseEntity<String>  responseBody = restTemplateUtility.getRestTemplate().exchange(url, HttpMethod.POST, entity, String.class);
            return responseBody.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String sendGetRequest(String url, HttpHeaders headers) {

        try {
            HttpEntity<Object> entity = new HttpEntity<>( headers);
            ResponseEntity<String>  responseBody = restTemplateUtility.getRestTemplate().exchange(url, HttpMethod.GET, entity, String.class);
            return responseBody.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
