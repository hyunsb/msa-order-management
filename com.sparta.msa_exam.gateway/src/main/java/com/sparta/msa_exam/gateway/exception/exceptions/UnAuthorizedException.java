package com.sparta.msa_exam.gateway.exception.exceptions;

public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException(String message) {
        super(message);
    }
}
