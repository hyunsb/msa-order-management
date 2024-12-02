package com.sparta.msa_exam.order.framework.feignclient.adapter;

import com.sparta.msa_exam.order.framework.feignclient.client.ProductClient;
import com.sparta.msa_exam.order.framework.feignclient.dto.ProductClientResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProductClientAdapter {

    private final ProductClient productClient;

    @CircuitBreaker(name = "product-service: createOrder", fallbackMethod = "fallbackGetProductIdIn")
    @Retry(name = "product-service:createOrder", fallbackMethod = "fallbackGetProductIdIn")
    public List<Long> getProductIdIn(List<Long> orderedProductIds) {
        log.info("######getProductIdIn try");
        return productClient.getProductIdIn(orderedProductIds).stream()
            .map(ProductClientResponse::getId)
            .toList();
    }

    public List<Long> fallbackGetProductIdIn(List<Long> orderedProductIds, Throwable t) {
        log.error("fallback: productClient error={}", t);
        throw new IllegalArgumentException("product-service 와의 통신이 원활하지 않습니다.");
    }
}
