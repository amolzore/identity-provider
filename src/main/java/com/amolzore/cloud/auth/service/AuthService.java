package com.amolzore.cloud.auth.service;

import com.amolzore.cloud.auth.client.UserManagerClient;
import com.amolzore.cloud.auth.dataaccess.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    public static final String SECRET = "cd+Pr1js+w2qfT2BoCD+tPcYp9LbjpmhSMEJqUob1mcxZ7+Wmik4AYdjX+DlDjmE4yporzQ9tm7v3z/j+QbdYg==";
    public static final long EXPIRATION_TIME_IN_MILLIS = 100; // 60 seconds = 60000
    public static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private final UserManagerClient userManagerClient;

    public JwtResponse generateUniqueToken(int id) {
        User user = userManagerClient.findById(id);
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MILLIS))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return new JwtResponse((token));
    }

    public Boolean validateToken(String token) {
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            Jwts.claims();
            if (user != null) {
                return true;
            }
        }
        return false;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class JwtResponse {
        String jwtToken;
    }
}