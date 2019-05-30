package com.amolzore.cloud.auth.client;

import com.amolzore.cloud.auth.dataaccess.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;

@Slf4j
@Component
public class UserManagerClient extends ApiGatewayClient {
    private static final Integer API_VERSION = 1;
    private static final String APP_NAME = "user";

    public UserManagerClient() {
        super(API_VERSION, APP_NAME);
    }

    public User findById(int id) {
        UriComponents uri = UriComponentsBuilder.fromPath("/" + id).build();
        LinkedHashMap body = (LinkedHashMap) get(uri.toUriString()).getBody();
        return new User((Integer) body.get("id"), (String) body.get("username"), (String) body.get("password"));
    }
}
