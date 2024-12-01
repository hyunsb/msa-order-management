package com.sparta.msa_exam.order.framework.cache.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CacheMissException extends RuntimeException {

    public CacheMissException(String message) {
        super(message);
    }
}
