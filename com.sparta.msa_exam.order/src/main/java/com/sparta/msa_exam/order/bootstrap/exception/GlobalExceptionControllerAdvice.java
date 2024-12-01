package com.sparta.msa_exam.order.bootstrap.exception;

import com.sparta.msa_exam.order.bootstrap.rest.dto.OrderApiResponse;
import com.sparta.msa_exam.order.bootstrap.rest.dto.OrderApiResponse.Error;
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
    private String serverPort;

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Error> runtimeExceptionHandle(RuntimeException exception) {
        log.error("runtimeException Handle= ", exception);
        return OrderApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), serverPort);
    }
}
