package com.sparta.msa_exam.order.framework.feignclient.client;

import com.sparta.msa_exam.order.framework.feignclient.dto.ProductClientResponse;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductClientFallback implements ProductClient {

    @Override
    public List<ProductClientResponse> getProductIdIn(List<Long> productIds) {
        log.error("fallback class 실행");
        return Collections.emptyList();
    }
}
