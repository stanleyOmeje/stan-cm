package com.stan.stancommons.service.http;

import org.apache.http.HttpResponse;

import java.util.Map;


public interface HttpService {

    <T> T postJson(String serviceURL, Map<String, String> params, Class<T> returnClass) throws Exception;


    <T> T getJson(String serviceURL, Map<String, String> params, Class<T> returnClass) throws Exception;


    <T> T postJsonAsString(String endpointURL, String content, Class<T> returnClass) throws Exception;


    String postForm(String paymentGatewayUrl, Map<String, Object> postLoad);


    <T> T postJsonStringWithBasicAuth(String remoteVendUrl, String json, Class<T> class1, String remoteVendUserName, String remoteVendPassword);


    HttpResponse postString(String data, String url) throws Exception;


    <T> T postJsonHandlerRedirect(String endPoint, Map<String, String> params, Class<T> responseClas) throws Exception;


    RemitaHttpResponse postJson(String endpointURL, String content) throws Exception;


    RemitaHttpResponse postXml(String endpointURL, String content);


    RemitaHttpResponse getJson(String endPoint, String resolvedRequestBody);


    RemitaHttpResponse getXml(String endPoint, String resolvedRequestBody);


    RemitaHttpResponse postXml(String endpointURL, Map<String, String> headers, String content);


    RemitaHttpResponse postXml(String endpointURL, Map<String, String> headers, String content, String contentType);


    RemitaHttpResponse postJson(String endpointURL, Map<String, String> headers, String content) throws Exception;


    String postJsonString(String endpointURL, String content) throws Exception;
}
