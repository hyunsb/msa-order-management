package com.sparta.msa_exam.gateway.exception.exceptions;

public class InvalidTokenException extends UnAuthorizedException {

    public InvalidTokenException(String message) {
        super(message);
    }
}
