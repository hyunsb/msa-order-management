package com.sparta.msa_exam.order.bootstrap.rest.dto;

import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderApiResponse {

    private static final String HEADER_SERVER_PORT = "Server-Port";

    public static ResponseEntity<Object> success(HttpStatus httpStatus, Integer port) {
        return ResponseEntity.status(httpStatus)
            .header(HEADER_SERVER_PORT, String.valueOf(port))
            .build();
    }

    public static <T> ResponseEntity<Success<T>> success(
        T data, HttpStatus httpStatus, Integer port
    ) {
        return ResponseEntity.status(httpStatus)
            .header(HEADER_SERVER_PORT, String.valueOf(port))
            .body(Success.of(data));
    }

    public static ResponseEntity<Object> successWithHeaders(
        HttpStatus httpStatus, Integer port, Map<String, String> headers
    ) {
        HttpHeaders httpHeaders = new HttpHeaders();
        headers.forEach(httpHeaders::add);
        httpHeaders.add(HEADER_SERVER_PORT, String.valueOf(port));

        return ResponseEntity.status(httpStatus)
            .headers(httpHeaders)
            .build();
    }

    public static ResponseEntity<Error> error(
        HttpStatus httpStatus, String message, Integer port
    ) {
        return ResponseEntity.status(httpStatus)
            .header(HEADER_SERVER_PORT, String.valueOf(port))
            .body(Error.of(message));
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Error {

        private String message;

        public static Error of(@NonNull String errorMessage) {
            return new Error(errorMessage);
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Success<T> {

        private T data;

        public static <T> Success<T> of(@NonNull T data) {
            return new Success<>(data);
        }
    }
}
