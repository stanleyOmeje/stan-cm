package com.stan.stancommons.util;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Collections;

@Component
public class RestTemplateUtility {

    static final Logger LOG = LoggerFactory.getLogger(RestTemplateUtility.class);

    @Value("${remita.proxy.host:127.0.0.1}")
    String host;

    @Value("${remita.proxy.port:3128}")
    int port;

    @Value("${remita.proxy.enable:false}")
    boolean proxyEnable;

    @Value("${remita.http.timeout:60000}")
    int timeout;

    @Value("${remita.http.timeout:20000}")
    int connectionTimeout;

    @Value("${remita.http.pool.max:400}")
    private int maxConnections;

    @Value("${remita.http.pool.max.perroute:400}")
    private int maxConnectionsPerRoute;

    @Value("${remita.http.pool.enabled:true}")
    private boolean poolingEnabled;

    @Autowired
    private RestTemplate restTemplate;

    public RestTemplate getLoadBalancedRestTemplate() {
        return restTemplate;
    }

    public RestTemplate getRestTemplate() {
        return getRestTemplate(connectionTimeout, timeout);
    }

    public RestTemplate getRestTemplateIgnoreSSL(int timeout, int connectionTimeout)
        throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContextBuilder sslcontext = new SSLContextBuilder();
        sslcontext.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        HttpClientBuilder httpClientBuilder = HttpClients
            .custom()
            .setSSLContext(sslcontext.build())
            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
            .setSSLSocketFactory(csf);

        if (proxyEnable) {
            if (poolingEnabled) {
                httpClientBuilder =
                    httpClientBuilder
                        .setProxy(new HttpHost(host, port, "http"))
                        .setMaxConnTotal(maxConnections)
                        .setMaxConnPerRoute(maxConnectionsPerRoute);
            } else {
                httpClientBuilder = httpClientBuilder.setProxy(new HttpHost(host, port, "http"));
            }
        } else {
            if (poolingEnabled) {
                httpClientBuilder = httpClientBuilder.setMaxConnTotal(maxConnections).setMaxConnPerRoute(maxConnectionsPerRoute);
            }
        }
        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        requestFactory.setReadTimeout(timeout);
        requestFactory.setConnectTimeout(connectionTimeout);
        requestFactory.setConnectionRequestTimeout(connectionTimeout + (connectionTimeout / 2));
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.setInterceptors(
            Collections.singletonList((request, body, execution) -> {
                String deviceId = MDC.get(MdcAwareLoggingFilter.DEVICE_REQUEST_ID_INTERNAL);
                String email = MDC.get(MdcAwareLoggingFilter.USER_EMAIL_INTERNAL);
                if (deviceId != null && !deviceId.isEmpty()) {
                    // headers.add(MdcAwareLoggingFilter.DEVICE_REQUEST_ID, deviceId);
                }
                if (email != null && !email.isEmpty()) {
                    // headers.add(MdcAwareLoggingFilter.USER_EMAIL, email);
                }
                return execution.execute(request, body);
            })
        );
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false; // Never treat any status as error
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                // Do nothing
            }
        });
        return restTemplate;
    }

    public RestTemplate getRestTemplateIgnoreSSL() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        try {
            return getRestTemplateIgnoreSSL(timeout, connectionTimeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public RestTemplate getRestTemplateIgnoreHostName() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        try {
            return getRestTemplateIgnoreHostName(timeout, connectionTimeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public RestTemplate getRestTemplateIgnoreHostName(int timeout, int connectionTimeout)
        throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        HostnameVerifier allPassVerifier = (String s, SSLSession sslSession) -> true; // ignore hostnaem checking
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build(); // keystore is null, not keystore is used at all
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, allPassVerifier);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        requestFactory.setReadTimeout(timeout);
        requestFactory.setConnectTimeout(connectionTimeout);
        requestFactory.setConnectionRequestTimeout(connectionTimeout + (connectionTimeout / 2));
        return new RestTemplate(requestFactory);
    }



    public RestTemplate getStreamRestTemplate() {
        return getStreamRestTemplate(connectionTimeout, timeout);
    }

    public RestTemplate getRestTemplate(int timeout, int connectionTimeout) {
        try {
            return getRestTemplateIgnoreSSL(timeout, connectionTimeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //    public RestTemplate getRestTemplate(int timeout, int connectionTimeout) {
    //        RestTemplate restTemplate = new RestTemplate();
    //        HttpClient httpClient = null;
    //        HttpComponentsClientHttpRequestFactory factory = null;
    //        if (proxyEnable) {
    //            if (poolingEnabled) {
    //                httpClient = HttpClientBuilder.create().setProxy(new HttpHost(host, port, "http")).setMaxConnTotal(maxConnections)
    //                        .setMaxConnPerRoute(maxConnectionsPerRoute).build();
    //            } else {
    //                httpClient = HttpClientBuilder.create().setProxy(new HttpHost(host, port, "http")).build();
    //            }
    //        } else {
    //            if (poolingEnabled) {
    //                httpClient = HttpClientBuilder.create().setMaxConnTotal(maxConnections).setMaxConnPerRoute(maxConnectionsPerRoute).build();
    //            }
    //        }
    //        factory = new HttpComponentsClientHttpRequestFactory(httpClient);
    //        factory.setReadTimeout(timeout);
    //        factory.setConnectTimeout(connectionTimeout);
    //        factory.setConnectionRequestTimeout(connectionTimeout + (connectionTimeout / 2));
    //        restTemplate.setRequestFactory(factory);
    //        restTemplate.setInterceptors(Collections.singletonList((request, body, execution) -> {
    //            String deviceId = MDC.get(MdcAwareLoggingFilter.DEVICE_REQUEST_ID_INTERNAL);
    //            String email = MDC.get(MdcAwareLoggingFilter.USER_EMAIL_INTERNAL);
    //            if (deviceId != null && !deviceId.isEmpty()) {
    //                // headers.add(MdcAwareLoggingFilter.DEVICE_REQUEST_ID, deviceId);
    //            }
    //            if (email != null && !email.isEmpty()) {
    //                // headers.add(MdcAwareLoggingFilter.USER_EMAIL, email);
    //            }
    //            return execution.execute(request, body);
    //        }));
    //        return restTemplate;
    //    }

    public RestTemplate getStreamRestTemplate(int timeout, int connectionTimeout) {
        RestTemplate streamRestTemplate = new RestTemplate();
        HttpClient httpClient = null;
        HttpComponentsClientHttpRequestFactory factory = null;
        if (proxyEnable) {
            if (poolingEnabled) {
                httpClient =
                    HttpClientBuilder
                        .create()
                        .setProxy(new HttpHost(host, port, "http"))
                        .setMaxConnTotal(maxConnections)
                        .setMaxConnPerRoute(maxConnectionsPerRoute)
                        .build();
            } else {
                httpClient = HttpClientBuilder.create().setProxy(new HttpHost(host, port, "http")).build();
            }
        } else {
            if (poolingEnabled) {
                httpClient = HttpClientBuilder.create().setMaxConnTotal(maxConnections).setMaxConnPerRoute(maxConnectionsPerRoute).build();
            }
        }
        factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        factory.setReadTimeout(timeout);
        factory.setConnectTimeout(connectionTimeout);
        factory.setConnectionRequestTimeout(connectionTimeout + (connectionTimeout / 2));
        streamRestTemplate.setRequestFactory(factory);
        streamRestTemplate.setInterceptors(
            Collections.singletonList((request, body, execution) -> {
                String deviceId = MDC.get(MdcAwareLoggingFilter.DEVICE_REQUEST_ID_INTERNAL);
                String email = MDC.get(MdcAwareLoggingFilter.USER_EMAIL_INTERNAL);
                if (deviceId != null && !deviceId.isEmpty()) {
                    // headers.add(MdcAwareLoggingFilter.DEVICE_REQUEST_ID, deviceId);
                }
                if (email != null && !email.isEmpty()) {
                    // headers.add(MdcAwareLoggingFilter.USER_EMAIL, email);
                }
                return execution.execute(request, body);
            })
        );
        return streamRestTemplate;
    }
}
