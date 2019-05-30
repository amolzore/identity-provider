package com.amolzore.cloud.auth.controller.v1;

import com.amolzore.cloud.auth.service.AuthService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static com.amolzore.cloud.auth.controller.ApiPath.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@RestController("authControllerV1")
@RequestMapping(API_V1_PATH + SERVICE_PATH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @RequestMapping(value = TOKEN_PATH, method = GET, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity generateUniqueToken(@ApiParam(value = "User ID", required = true)
                                              @PathVariable("id") int userId,
                                              HttpServletResponse response) throws Exception {
        return new ResponseEntity(authService.generateUniqueToken(userId), HttpStatus.OK);
    }

    @RequestMapping(value = VALIDATE_PATH, method = POST, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity validateToken(@RequestParam(value = "token") String token) throws Exception {
        return new ResponseEntity(authService.validateToken(token), HttpStatus.OK);
    }
}