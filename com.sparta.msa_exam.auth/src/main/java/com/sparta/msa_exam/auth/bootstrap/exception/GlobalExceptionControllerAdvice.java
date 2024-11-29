package com.sparta.msa_exam.auth.bootstrap.exception;

import com.sparta.msa_exam.auth.bootstrap.rest.dto.AuthApiResponse;
import com.sparta.msa_exam.auth.bootstrap.rest.dto.AuthApiResponse.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionControllerAdvice {

    @Value("${server.port}")
    private Integer serverPort;

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Error> runtimeExceptionHandle(RuntimeException exception) {
        log.error("runtimeException Handle= ", exception);
        return AuthApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), serverPort);
    }
}
