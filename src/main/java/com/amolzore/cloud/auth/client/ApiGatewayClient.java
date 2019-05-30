package com.amolzore.cloud.auth.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class ApiGatewayClient {
    private String API_GATEWAY_URL = "http://localhost:8087";
    private final static String BASE_URL = "/api";
    private final String appName;
    private String apiVersion;
    @Autowired
    RestTemplate restTemplate;


    public ApiGatewayClient(Integer apiVersion, String appName) {
        this.appName = appName;
        this.apiVersion = "v" + apiVersion;
    }

    protected String getBaseURL() {
        return BASE_URL + "/" + apiVersion + "/" + appName;
    }

    public ResponseEntity post(String relativeURL, final Object object) throws RestClientException {
        return restTemplate.postForEntity(API_GATEWAY_URL + getBaseURL() + relativeURL, object, Object.class);
    }

    public <T> ResponseEntity<T> post(final String relativeURL, final Object object, final Class<T> responseType) throws RestClientException {
        return restTemplate.postForEntity(API_GATEWAY_URL + getBaseURL() + relativeURL, object, responseType);
    }

    public ResponseEntity get(String relativeURL) throws RestClientException {
        return restTemplate.getForEntity(API_GATEWAY_URL + getBaseURL() + relativeURL, Object.class);
    }
}
