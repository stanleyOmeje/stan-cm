package com.stan.stancommons.service.http;

import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;


public interface SoapWebService {

    public RemitaHttpResponse sendSoapMessage(String payload, String endPoint);

    public RemitaHttpResponse sendPostIgnoreSSL(String payload, String endPoint)
            throws ClientProtocolException, IOException, NoSuchAlgorithmException, KeyManagementException;

    public RemitaHttpResponse sendPostIgnoreSSL(String payload, String endPoint, Map<String, String> headers)
            throws ClientProtocolException, IOException, NoSuchAlgorithmException, KeyManagementException;
}
