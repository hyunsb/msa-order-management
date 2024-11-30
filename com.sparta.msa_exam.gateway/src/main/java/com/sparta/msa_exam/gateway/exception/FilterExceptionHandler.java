package com.sparta.msa_exam.gateway.exception;

import com.sparta.msa_exam.gateway.exception.ErrorResponse.Error;
import com.sparta.msa_exam.gateway.exception.exceptions.UnAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class FilterExceptionHandler {

    @Value("${server.port}")
    private String port;

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<Error> runtimeExceptionHandle(UnAuthorizedException exception) {
        log.error("UnAuthorizedException Handle= ", exception);
        return ErrorResponse.error(HttpStatus.UNAUTHORIZED, exception.getMessage(), port);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Error> runtimeExceptionHandle(RuntimeException exception) {
        log.error("runtimeException Handle= ", exception);
        return ErrorResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), port);
    }
}