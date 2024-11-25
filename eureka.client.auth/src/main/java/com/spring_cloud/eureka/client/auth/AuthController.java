package com.spring_cloud.eureka.client.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/auth/signIn")
    public AuthResponse createAuthenticationToken(@RequestBody SignInRequest signInRequest) {
        log.info("signInRequest= {}", signInRequest);
        String token = authService.signIn(signInRequest.getUserId(), signInRequest.getPassword());
        return new AuthResponse(token);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/auth/signUp")
    public User signUp(@RequestBody User user) {
        log.info("user= {}", user);
        return authService.signUp(user);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class AuthResponse {
        private String accessToken;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class SignInRequest {
        private String userId;
        private String password;
    }
}
