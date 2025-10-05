package com.stan.stancommons.service.http;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.Map.Entry;


@Component
@Scope("prototype")
public class HttpServiceImpl implements HttpService, InitializingBean {

    private static final Log LOG = LogFactory.getLog(HttpServiceImpl.class);

    @Value("${http.connection.timeout:1000000}")
    private int connectionTimeout;

    @Value("${http.read.timeout:10000}")
    private int readTimeout;

    @Value("${http.proxy.enabled:false}")
    private boolean proxyEnabled;

    @Value("${http.proxy.port:80}")
    private String proxyServerPort;

    @Value("${http.proxy.host:127.0.0.1}")
    private String proxyServer;

    @Value("${http.max.connections:200}")
    private int maxConnection;

    private final Gson gson = new Gson();

    private CloseableHttpClient httpclient;

    private RequestConfig requestConfig;

    private PoolingHttpClientConnectionManager poolManager;


    @Override
    public <T> T postJson(String endpointURL, Map<String, String> params, final Class<T> returnClass) throws Exception {
        CloseableHttpResponse httpResponse = null;
        ResponseHandler<T> responseHandler = new ResponseHandler<T>() {

            @Override
            public T handleResponse(final HttpResponse response) throws IOException {
                T responseT = null;
                StatusLine statusLine = response.getStatusLine();
                HttpEntity entity = response.getEntity();
                if ((statusLine.getStatusCode() >= 300) && (statusLine.getStatusCode() != 401)) {
                    throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
                }
                if (entity == null) {
                    throw new ClientProtocolException("Response contains no content");
                }
                String json = EntityUtils.toString(entity);
                EntityUtils.consumeQuietly(entity);
                Object obj = json;
                if (StringUtils.isNotBlank(json)) {
                    if (!returnClass.equals(String.class)) {
                        obj = gson.fromJson(json, returnClass);
                    }
                } else {
                    try {
                        obj = ConstructorUtils.invokeConstructor(returnClass);
                    } catch (Exception e) {
                    }
                }
                responseT = (T) obj;
                return responseT;
            }
        };
        RequestConfig requestConfig = getRequestConfig();
        CloseableHttpClient httpclient = getHttpClient();
        T httpCallResponse = null;
        try {
            HttpPost httpPost = new HttpPost(endpointURL);
            httpPost.setConfig(requestConfig);
            if (params != null) {
                List<NameValuePair> nvps = new ArrayList<>();
                for (Entry<String, String> param : params.entrySet()) {
                    nvps.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
            }
            httpCallResponse = httpclient.execute(httpPost, responseHandler);
        } catch (Exception e) {
            HttpServiceImpl.LOG.error(e);
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (Exception e) {
                }
            }
        }
        return httpCallResponse;
    }


    @Override
    public <T> T postJsonAsString(String endpointURL, String content, final Class<T> returnClass) throws Exception {
        T httpCallResponse = null;
        CloseableHttpResponse httpResponse = null;
        ResponseHandler<T> responseHandler = new ResponseHandler<T>() {

            @Override
            public T handleResponse(final HttpResponse response) throws IOException {
                T responseT = null;
                StatusLine statusLine = response.getStatusLine();
                HttpEntity entity = response.getEntity();
                if ((statusLine.getStatusCode() >= 300) && (statusLine.getStatusCode() != 401)) {
                    throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
                }
                if (entity == null) {
                    throw new ClientProtocolException("Response contains no content");
                }
                String json = EntityUtils.toString(entity);
                EntityUtils.consumeQuietly(entity);
                Object obj = null;
                if (StringUtils.isNotBlank(json)) {
                    obj = gson.fromJson(json, returnClass);
                } else {
                    try {
                        obj = ConstructorUtils.invokeConstructor(returnClass);
                    } catch (Exception e) {
                    }
                }
                responseT = (T) obj;
                return responseT;
            }
        };
        RequestConfig requestConfig = getRequestConfig();
        CloseableHttpClient httpclient = getHttpClient();
        try {
            HttpPost httpPost = new HttpPost(endpointURL);
            httpPost.setConfig(requestConfig);
            if (content != null) {
                // HttpEntity repo = null;
                // repo = new ByteArrayEntity(content.getBytes("UTF-8"));
                //
                StringEntity entity = new StringEntity(content.toString(), HTTP.UTF_8);
                entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                httpPost.setEntity(entity);
                httpCallResponse = httpclient.execute(httpPost, responseHandler);
            }
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (Exception e) {
                }
            }
        }
        return httpCallResponse;
    }


    @Override
    public <T> T getJson(String serviceURL, Map<String, String> params, final Class<T> returnClass) throws Exception {
        CloseableHttpResponse httpResponse = null;
        ResponseHandler<T> responseHandler = new ResponseHandler<T>() {

            @Override
            public T handleResponse(final HttpResponse response) throws IOException {
                T responseT = null;
                StatusLine statusLine = response.getStatusLine();
                HttpEntity entity = response.getEntity();
                if ((statusLine.getStatusCode() >= 300) && (statusLine.getStatusCode() != 401)) {
                    throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
                }
                if (entity == null) {
                    throw new ClientProtocolException("Response contains no content");
                }
                String json = EntityUtils.toString(entity);
                EntityUtils.consumeQuietly(entity);
                Object obj = json;
                if (StringUtils.isNotBlank(json)) {
                    if (!returnClass.equals(String.class)) {
                        obj = gson.fromJson(json, returnClass);
                    }
                } else {
                    try {
                        obj = ConstructorUtils.invokeConstructor(returnClass);
                    } catch (Exception e) {
                    }
                }
                responseT = (T) obj;
                return responseT;
            }
        };
        if (httpclient == null) {
            requestConfig = getRequestConfig();
            httpclient = getHttpClient();
        }
        T httpCallResponse = null;
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> nvps = new ArrayList<>();
                for (Entry<String, String> param : params.entrySet()) {
                    nvps.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
                String paramString = URLEncodedUtils.format(nvps, "utf-8");
                serviceURL += paramString;
            }
            HttpGet httpPost = new HttpGet(serviceURL);
            httpPost.setConfig(requestConfig);
            httpCallResponse = httpclient.execute(httpPost, responseHandler);
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (Exception e) {
                }
            }
        }
        return httpCallResponse;
    }


    @Override
    public String postForm(String paymentGatewayUrl, Map<String, Object> postLoad) {
        String response = StringUtils.EMPTY;
        CloseableHttpResponse httpResponse = null;
        try {
            ResponseHandler<String> rh = new ResponseHandler<String>() {

                @Override
                public String handleResponse(final HttpResponse response) throws IOException {
                    String resp = null;
                    StatusLine statusLine = response.getStatusLine();
                    response.getEntity();
                    if (statusLine.getStatusCode() == 200) {
                        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                        StringBuffer result = new StringBuffer();
                        String line = StringUtils.EMPTY;
                        while ((line = rd.readLine()) != null) {
                            result.append(line);
                        }
                        resp = result.toString();
                    } else {
                        HttpServiceImpl.LOG
                                .debug(String.format("Error posting to server Response :%s %s", statusLine.getStatusCode(), statusLine.getReasonPhrase()));
                    }
                    return resp;
                }
            };
            CloseableHttpClient httpclient = getHttpClient();
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout).setConnectTimeout(connectionTimeout).build();
            HttpPost post = new HttpPost(paymentGatewayUrl);
            post.setConfig(requestConfig);
            // add header
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
            List<NameValuePair> postParams = new ArrayList<>();
            Set<Entry<String, Object>> e = postLoad.entrySet();
            for (Entry<String, Object> ee : e) {
                if (ee.getValue() != null) {
                    postParams.add(new BasicNameValuePair(ee.getKey(), String.valueOf(ee.getValue())));
                }
            }
            String body = URLEncodedUtils.format(postParams, "UTF-8"); // use encoding of request
            StringEntity en = new StringEntity(body);
            // HttpEntity en = new UrlEncodedFormEntity(postParams);
            post.setEntity(en);
            response = httpclient.execute(post, rh);
        } catch (Exception e) {
            HttpServiceImpl.LOG.error(e);
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (Exception e) {
                }
            }
        }
        return response;
    }


    private RequestConfig getRequestConfig() {
        RequestConfig requestConfig = null;
        if (proxyEnabled && StringUtils.isNotBlank(proxyServer) && StringUtils.isNotBlank(proxyServerPort) && StringUtils.isNumeric(proxyServerPort)) {
            System.setProperty("http.proxySet", "true");
            System.setProperty("http.proxyHost", proxyServer);
            System.setProperty("http.proxyPort", proxyServerPort);
            // System.getProperties().put("http.nonProxyHosts", "localhost|127.0.0.1");
            HttpHost proxy = new HttpHost(proxyServer, Integer.parseInt(proxyServerPort));
            requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout).setConnectTimeout(connectionTimeout).setProxy(proxy).build();
        } else {
            requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout).setConnectTimeout(connectionTimeout).build();
        }
        return requestConfig;
    }


    private CloseableHttpClient getHttpClient() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        SSLConnectionSocketFactory sslsf = getSocketFactory();
        CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(poolManager).setSSLSocketFactory(sslsf)
                .setHostnameVerifier(new AllowAllHostnameVerifier()).setRedirectStrategy(new PGLaxRedirectStrategy())
                .setRedirectStrategy(new LaxRedirectStrategy()).build();
        return httpclient;
    }


    private SSLConnectionSocketFactory getSocketFactory() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        SSLContextBuilder sslBuilder = new SSLContextBuilder();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslBuilder.build());
        sslBuilder.loadTrustMaterial(null, new TrustStrategy() {

            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        });
        return sslsf;
    }


    private class PGLaxRedirectStrategy extends LaxRedirectStrategy {

        public PGLaxRedirectStrategy() {
            super();
        }


        @Override
        protected URI createLocationURI(String location) throws ProtocolException {
            if (location.contains("?")) {
                String[] qry = location.split("\\?");
                if (qry.length == 2) {
                    // try {
                    // location = URIUtil.encodeQuery(location);
                    // } catch (URIException e) {
                    // HttpServiceImpl.LOG.error(e);
                    // }
                }
            }
            return super.createLocationURI(location);
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustStrategy() {

            @Override
            public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                return true;
            }
        });
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", new PlainConnectionSocketFactory())
                .register("https", sslsf).build();
        poolManager = new PoolingHttpClientConnectionManager(registry);
        poolManager.setMaxTotal(maxConnection);
    }


    @Override
    public <T> T postJsonStringWithBasicAuth(String endpointURL, String content, final Class<T> returnClass, String userName, String password) {
        // TODO Auto-generated method stub
        T httpCallResponse = null;
        try {
            URL aURL = new URL(endpointURL);
            System.out.println("protocol = " + aURL.getProtocol());
            System.out.println("authority = " + aURL.getAuthority());
            System.out.println("host = " + aURL.getHost());
            System.out.println("port = " + aURL.getPort());
            System.out.println("path = " + aURL.getPath());
            System.out.println("query = " + aURL.getQuery());
            System.out.println("filename = " + aURL.getFile());
            System.out.println("ref = " + aURL.getRef());
            CloseableHttpResponse httpResponse = null;
            ResponseHandler<T> responseHandler = new ResponseHandler<T>() {

                @Override
                public T handleResponse(final HttpResponse response) throws IOException {
                    T responseT = null;
                    StatusLine statusLine = response.getStatusLine();
                    HttpEntity entity = response.getEntity();
                    if ((statusLine.getStatusCode() >= 300) && (statusLine.getStatusCode() != 401)) {
                        throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
                    }
                    if (entity == null) {
                        throw new ClientProtocolException("Response contains no content");
                    }
                    String json = EntityUtils.toString(entity);
                    EntityUtils.consumeQuietly(entity);
                    Object obj = null;
                    if (StringUtils.isNotBlank(json)) {
                        try {
                            obj = gson.fromJson(json, returnClass);
                        } catch (Exception e) {
                            HttpServiceImpl.LOG.error(e);
                        }
                    } else {
                        try {
                            obj = ConstructorUtils.invokeConstructor(returnClass);
                        } catch (Exception e) {
                        }
                    }
                    responseT = (T) obj;
                    return responseT;
                }
            };
            RequestConfig requestConfig = getRequestConfig();
            try {
                HttpGet httpPost = new HttpGet(endpointURL);
                HttpHost targetHost = new HttpHost(aURL.getHost());
                targetHost = new HttpHost(aURL.getHost(), aURL.getPort(), aURL.getProtocol());
                CredentialsProvider credsProvider = new BasicCredentialsProvider();
                credsProvider.setCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                        new UsernamePasswordCredentials(userName, password));
                AuthCache authCache = new BasicAuthCache();
                authCache.put(targetHost, new BasicScheme());
                // Add AuthCache to the execution context
                final HttpClientContext context = HttpClientContext.create();
                context.setCredentialsProvider(credsProvider);
                context.setAuthCache(authCache);
                httpPost.setConfig(requestConfig);
                // if (content != null) {
                // HttpEntity repo = null;
                // repo = new ByteArrayEntity(content.getBytes("UTF-8"));
                //
                httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json");
                CloseableHttpClient httpclient = getHttpClient(credsProvider);
                httpCallResponse = httpclient.execute(httpPost, responseHandler);
                // }
            } catch (Exception e) {
                HttpServiceImpl.LOG.error(e);
                HttpServiceImpl.LOG.error(e);
            } finally {
                if (httpResponse != null) {
                    try {
                        httpResponse.close();
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
        return httpCallResponse;
    }


    public <T> T getJsonStringWithBasicAuth(String endpointURL, String content, final Class<T> returnClass, String userName, String password) {
        // TODO Auto-generated method stub
        T httpCallResponse = null;
        try {
            URL aURL = new URL(endpointURL);
            System.out.println("protocol = " + aURL.getProtocol());
            System.out.println("authority = " + aURL.getAuthority());
            System.out.println("host = " + aURL.getHost());
            System.out.println("port = " + aURL.getPort());
            System.out.println("path = " + aURL.getPath());
            System.out.println("query = " + aURL.getQuery());
            System.out.println("filename = " + aURL.getFile());
            System.out.println("ref = " + aURL.getRef());
            CloseableHttpResponse httpResponse = null;
            ResponseHandler<T> responseHandler = new ResponseHandler<T>() {

                @Override
                public T handleResponse(final HttpResponse response) throws IOException {
                    T responseT = null;
                    StatusLine statusLine = response.getStatusLine();
                    HttpEntity entity = response.getEntity();
                    if ((statusLine.getStatusCode() >= 300) && (statusLine.getStatusCode() != 401)) {
                        throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
                    }
                    if (entity == null) {
                        throw new ClientProtocolException("Response contains no content");
                    }
                    String json = EntityUtils.toString(entity);
                    EntityUtils.consumeQuietly(entity);
                    Object obj = null;
                    if (StringUtils.isNotBlank(json)) {
                        try {
                            obj = gson.fromJson(json, returnClass);
                        } catch (Exception e) {
                            HttpServiceImpl.LOG.error(e);
                        }
                    } else {
                        try {
                            obj = ConstructorUtils.invokeConstructor(returnClass);
                        } catch (Exception e) {
                        }
                    }
                    responseT = (T) obj;
                    return responseT;
                }
            };
            RequestConfig requestConfig = getRequestConfig();
            try {
                HttpGet httpPost = new HttpGet(endpointURL);
                HttpHost targetHost = new HttpHost(aURL.getHost());
                targetHost = new HttpHost(aURL.getHost(), aURL.getPort(), aURL.getProtocol());
                CredentialsProvider credsProvider = new BasicCredentialsProvider();
                credsProvider.setCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                        new UsernamePasswordCredentials(userName, password));
                AuthCache authCache = new BasicAuthCache();
                authCache.put(targetHost, new BasicScheme());
                // Add AuthCache to the execution context
                final HttpClientContext context = HttpClientContext.create();
                context.setCredentialsProvider(credsProvider);
                context.setAuthCache(authCache);
                httpPost.setConfig(requestConfig);
                // if (content != null) {
                // HttpEntity repo = null;
                // repo = new ByteArrayEntity(content.getBytes("UTF-8"));
                //
                httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json");
                CloseableHttpClient httpclient = getHttpClient(credsProvider);
                httpCallResponse = httpclient.execute(httpPost, responseHandler);
                // }
            } catch (Exception e) {
                HttpServiceImpl.LOG.error(e);
                HttpServiceImpl.LOG.error(e);
            } finally {
                if (httpResponse != null) {
                    try {
                        httpResponse.close();
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
        return httpCallResponse;
    }


    private CloseableHttpClient getHttpClient(CredentialsProvider credsProvider) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        SSLConnectionSocketFactory sslsf = getSocketFactory();
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).setConnectionManager(poolManager)
                .setSSLSocketFactory(sslsf).setHostnameVerifier(new AllowAllHostnameVerifier()).setRedirectStrategy(new PGLaxRedirectStrategy()).build();
        return httpclient;
    }


    @Override
    public HttpResponse postString(String data, String url) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public <T> T postJsonHandlerRedirect(String endPoint, Map<String, String> params, Class<T> responseClas) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public String postJsonString(String endpointURL, String content) throws Exception {
        CloseableHttpResponse httpResponse = null;
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            @Override
            public String handleResponse(final HttpResponse response) throws IOException {
                String responseT = null;
                StatusLine statusLine = response.getStatusLine();
                HttpEntity entity = response.getEntity();
                if (entity == null) {
                    throw new ClientProtocolException("Response contains no content");
                }
                String json = EntityUtils.toString(entity);
                return json;
            }
        };
        HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {

            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                exception.printStackTrace();
                if (executionCount >= 5) {
                    // Do not retry if over max retry count
                    return false;
                }
                if (exception instanceof InterruptedIOException) {
                    // Timeout
                    return false;
                }
                if (exception instanceof UnknownHostException) {
                    // Unknown host
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {
                    // Connection refused
                    return false;
                }
                if (exception instanceof SSLException) {
                    // SSL handshake exception
                    return false;
                }
                return false;
            }
        };
        RequestConfig d = null;
        if (proxyEnabled && StringUtils.isNotBlank(proxyServer) && StringUtils.isNotBlank(proxyServerPort) && StringUtils.isNumeric(proxyServerPort)) {
            HttpHost proxy = new HttpHost(proxyServer, Integer.parseInt(proxyServerPort));
            requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout).setConnectTimeout(connectionTimeout).setProxy(proxy).build();
        } else {
            requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout).setConnectTimeout(connectionTimeout).build();
        }
        CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(poolManager).setRetryHandler(requestRetryHandler).build();
        String httpCallResponse = null;
        try {
            HttpPost httpPost = new HttpPost(endpointURL);
            httpPost.setConfig(requestConfig);
            if (content != null) {
                StringEntity entity = new StringEntity(content.toString(), HTTP.UTF_8);
                entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                httpPost.setEntity(entity);
                httpCallResponse = httpclient.execute(httpPost, responseHandler);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return httpCallResponse;
    }


    @Override
    public RemitaHttpResponse postXml(String endpointURL, String content) {
        Map<String, String> headers = new HashMap();
        return postXml(endpointURL, headers, content, "application/xml");
    }


    @Override
    public RemitaHttpResponse getJson(String endPoint, String resolvedRequestBody) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public RemitaHttpResponse getXml(String endPoint, String resolvedRequestBody) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public RemitaHttpResponse postXml(String endpointURL, Map<String, String> headers, String content) {
        return postXml(endpointURL, headers, content, "application/xml");
    }


    @Override
    public RemitaHttpResponse postXml(String endpointURL, Map<String, String> headers, String content, String contentType) {
        RemitaHttpResponse httpCallResponse = new RemitaHttpResponse();
        try {
            CloseableHttpResponse httpResponse = null;
            ResponseHandler<RemitaHttpResponse> responseHandler = new ResponseHandler<RemitaHttpResponse>() {

                @SuppressWarnings("unchecked")
                @Override
                public RemitaHttpResponse handleResponse(final HttpResponse response) throws IOException {
                    RemitaHttpResponse responseT = new RemitaHttpResponse();
                    StatusLine statusLine = response.getStatusLine();
                    responseT.setResponseCode(statusLine.getStatusCode());
                    HttpEntity entity = response.getEntity();
                    if ((statusLine.getStatusCode() >= 300) && (statusLine.getStatusCode() != 401)) {
                        // throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
                    }
                    if (entity == null) {
                        // throw new ClientProtocolException("Response contains no content");
                    }
                    String json = EntityUtils.toString(entity);
                    EntityUtils.consumeQuietly(entity);
                    HttpServiceImpl.LOG.debug("Response from HTTP :" + json);
                    responseT.setResponseBodyAsString(json);
                    return responseT;
                }
            };
            HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {

                @Override
                public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                    exception.printStackTrace();
                    if (executionCount >= 5) {
                        // Do not retry if over max retry count
                        return false;
                    }
                    if (exception instanceof InterruptedIOException) {
                        // Timeout
                        return false;
                    }
                    if (exception instanceof UnknownHostException) {
                        // Unknown host
                        return false;
                    }
                    if (exception instanceof ConnectTimeoutException) {
                        // Connection refused
                        return false;
                    }
                    if (exception instanceof SSLException) {
                        // SSL handshake exception
                        return false;
                    }
                    return false;
                }
            };
            RequestConfig requestConfig = getRequestConfig();
            CloseableHttpClient httpclient = getHttpClient();
            // CloseableHttpClient httpclient =
            // HttpClients.custom().setConnectionManager(poolManager).setRetryHandler(requestRetryHandler).build();
            try {
                HttpPost httpPost = new HttpPost(endpointURL);
                httpPost.setConfig(requestConfig);
                if (content != null) {
                    HttpServiceImpl.LOG.debug("Posting >>>> " + content);
                    @SuppressWarnings("deprecation")
                    ByteArrayEntity entity = new ByteArrayEntity(content.getBytes("UTF-8"));
                    entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, contentType));
                    httpPost.setEntity(entity);
                    if (headers != null && !headers.isEmpty()) {
                        Set<Entry<String, String>> eSets = headers.entrySet();
                        for (Entry<String, String> eSet : eSets) {
                            httpPost.setHeader(eSet.getKey(), eSet.getValue());
                        }
                    }
                    httpCallResponse = httpclient.execute(httpPost, responseHandler);
                }
            } catch (Exception e) {
                HttpServiceImpl.LOG.error(e);
            } finally {
                if (httpResponse != null) {
                    try {
                        httpResponse.close();
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
        return httpCallResponse;
    }


    @Override
    public RemitaHttpResponse postJson(String endpointURL, Map<String, String> headers, String content) throws Exception {
        RemitaHttpResponse httpCallResponse = new RemitaHttpResponse();
        try {
            CloseableHttpResponse httpResponse = null;
            ResponseHandler<RemitaHttpResponse> responseHandler = new ResponseHandler<RemitaHttpResponse>() {

                @SuppressWarnings("unchecked")
                @Override
                public RemitaHttpResponse handleResponse(final HttpResponse response) throws IOException {
                    RemitaHttpResponse responseT = new RemitaHttpResponse();
                    StatusLine statusLine = response.getStatusLine();
                    responseT.setResponseCode(statusLine.getStatusCode());
                    HttpEntity entity = response.getEntity();
                    if ((statusLine.getStatusCode() >= 300) && (statusLine.getStatusCode() != 401)) {
                        // throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
                    }
                    if (entity == null) {
                        // throw new ClientProtocolException("Response contains no content");
                    }
                    String json = EntityUtils.toString(entity);
                    EntityUtils.consumeQuietly(entity);
                    responseT.setResponseBodyAsString(json);
                    return responseT;
                }
            };
            RequestConfig requestConfig = getRequestConfig();
            CloseableHttpClient httpclient = getHttpClient();
            try {
                HttpPost httpPost = new HttpPost(endpointURL);
                httpPost.setConfig(requestConfig);
                if (content != null) {
                    // HttpEntity repo = null;
                    // repo = new ByteArrayEntity(content.getBytes("UTF-8"));
                    //
                    @SuppressWarnings("deprecation")
                    StringEntity entity = new StringEntity(content.toString(), HTTP.UTF_8);
                    entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    httpPost.setEntity(entity);
                    if (headers != null && !headers.isEmpty()) {
                        Set<Entry<String, String>> eSets = headers.entrySet();
                        for (Entry<String, String> eSet : eSets) {
                            httpPost.setHeader(eSet.getKey(), eSet.getValue());
                        }
                    }
                    httpCallResponse = httpclient.execute(httpPost, responseHandler);
                }
            } catch (Exception e) {
                HttpServiceImpl.LOG.error(e);
            } finally {
                if (httpResponse != null) {
                    try {
                        httpResponse.close();
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
        return httpCallResponse;
    }


    @Override
    public RemitaHttpResponse postJson(String endpointURL, String content) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
}
