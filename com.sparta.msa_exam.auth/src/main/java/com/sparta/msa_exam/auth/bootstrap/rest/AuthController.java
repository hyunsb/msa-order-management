package com.sparta.msa_exam.auth.bootstrap.rest;

import com.sparta.msa_exam.auth.application.service.AuthService;
import com.sparta.msa_exam.auth.bootstrap.rest.dto.AuthApiResponse;
import com.sparta.msa_exam.auth.bootstrap.rest.dto.SignInRequest;
import com.sparta.msa_exam.auth.bootstrap.rest.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @Value("${server.port}")
    private Integer serverPort;

    @PostMapping("/signIn")
    public ResponseEntity<Object> signIn(@RequestBody SignInRequest signInRequest) {
        String accessToken = authService.signIn(signInRequest.toDomain());
        return AuthApiResponse.successWithAccessToken(HttpStatus.NO_CONTENT, serverPort, accessToken);
    }

    @PostMapping("/signUp")
    public ResponseEntity<Object> signUp(@RequestBody SignUpRequest signUpRequest) {
        authService.signUp(signUpRequest.toDomain());
        return AuthApiResponse.success(HttpStatus.NO_CONTENT, serverPort);
    }
}
