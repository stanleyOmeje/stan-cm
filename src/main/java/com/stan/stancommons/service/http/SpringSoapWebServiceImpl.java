package com.stan.stancommons.service.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


@Component
@Scope("prototype")
@Slf4j
public class SpringSoapWebServiceImpl implements SoapWebService {

    private static final Log LOG = LogFactory.getLog(SpringSoapWebServiceImpl.class);

    private int responseCode = 200;

    @Value("${http.proxy.enabled:false}")
    private boolean proxyEnabled;

    @Value("${http.proxy.port:80}")
    private String proxyServerPort;

    @Value("${http.proxy.host:127.0.0.1}")
    private String proxyServer;

    @Value("${http.connection.timeout:10000}")
    private int connectionTimeout;

    @Value("${http.read.timeout:10000}")
    private int readTimeout;


    @Override
    public RemitaHttpResponse sendSoapMessage(String payload, String endPoint) {
        RemitaHttpResponse response = new RemitaHttpResponse();
        /*
         * SoapClient client = SoapClient.builder().endpointUri(endPoint).build(); //
         * client.builder().endpointSecurity(""). System.out.println("Soap payload" + payload); String strResult =
         * client.post(payload); response.setResponseBodyAsString(strResult); response.setResponseCode(responseCode);
         * System.out.println(response.getResponseBodyAsString());
         */
        return response;
    }


    @Override
    public RemitaHttpResponse sendPostIgnoreSSL(String getPayload, String endPoint) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        RemitaHttpResponse httpResponse = new RemitaHttpResponse();
        System.out.println("Payload>>>>> " + getPayload);
        String payload = trimXml(getPayload);
        System.out.println("Strimmed Payload>>>>> " + payload);
        String result = null;
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[]{new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                System.out.println("getAcceptedIssuers =============");
                return null;
            }


            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                System.out.println("checkClientTrusted =============");
            }


            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                System.out.println("checkServerTrusted =============");
            }
        }}, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        HostnameVerifier allHostsValid = new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        RequestConfig requestConfig = getRequestConfig();
        HttpPost httpPost = new HttpPost(endPoint);
        httpPost.setConfig(requestConfig);
        ByteArrayEntity postDataEntity = new ByteArrayEntity(payload.getBytes());
        httpPost.setEntity(postDataEntity);
        CloseableHttpResponse response = httpclient.execute(httpPost);
        try {
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        httpResponse.setResponseBodyAsString(result);
        httpResponse.setResponseCode(responseCode);
        return httpResponse;
    }


    private RequestConfig getRequestConfig() {
        RequestConfig requestConfig = null;
        if (proxyEnabled && StringUtils.isNotBlank(proxyServer) && StringUtils.isNotBlank(proxyServerPort) && StringUtils.isNumeric(proxyServerPort)) {
            System.setProperty("http.proxySet", "true");
            System.setProperty("http.proxyHost", proxyServer);
            System.setProperty("http.proxyPort", proxyServerPort);
            HttpHost proxy = new HttpHost(proxyServer, Integer.parseInt(proxyServerPort));
            requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout).setConnectTimeout(connectionTimeout).setProxy(proxy).build();
        } else {
            requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout).setConnectTimeout(connectionTimeout).build();
        }
        return requestConfig;
    }


    public String trimXml(String input) {
        BufferedReader reader = new BufferedReader(new StringReader(input));
        StringBuffer result = new StringBuffer();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line.trim());
            }
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public RemitaHttpResponse sendPostIgnoreSSL(String getPayload, String endPoint, Map<String, String> headers)
            throws ClientProtocolException, IOException, NoSuchAlgorithmException, KeyManagementException {
        RemitaHttpResponse httpResponse = new RemitaHttpResponse();
        System.out.println("Payload>>>>> " + getPayload);
        String payload = trimXml(getPayload);
        System.out.println("Strimmed Payload>>>>> " + payload);
        String result = null;
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[]{new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                System.out.println("getAcceptedIssuers =============");
                return null;
            }


            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                System.out.println("checkClientTrusted =============");
            }


            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                System.out.println("checkServerTrusted =============");
            }
        }}, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        HostnameVerifier allHostsValid = new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        RequestConfig requestConfig = getRequestConfig();
        HttpPost httpPost = new HttpPost(endPoint);
        httpPost.setConfig(requestConfig);
        ByteArrayEntity postDataEntity = new ByteArrayEntity(payload.getBytes());
        httpPost.setEntity(postDataEntity);
        if (headers != null && !headers.isEmpty()) {
            Set<Entry<String, String>> eSets = headers.entrySet();
            for (Entry<String, String> eSet : eSets) {
                httpPost.setHeader(eSet.getKey(), eSet.getValue());
            }
        }
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            log.info("Request Timeout Vending payload {} to endPoint {} ", getPayload, endPoint);
            result = "Request Timeout";
            responseCode = 408;
        } catch (IOException e) {
            e.printStackTrace();
        }
        httpResponse.setResponseBodyAsString(result);
        httpResponse.setResponseCode(responseCode);
        log.info("Request Response payload {} to endPoint {} ", result, endPoint);
        return httpResponse;
    }
}
