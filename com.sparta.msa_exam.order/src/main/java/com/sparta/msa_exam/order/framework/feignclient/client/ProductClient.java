package com.sparta.msa_exam.order.framework.feignclient.client;

import com.sparta.msa_exam.order.framework.config.FeignConfig;
import com.sparta.msa_exam.order.framework.feignclient.dto.ProductClientResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Primary
@FeignClient(name = "product-service",
             configuration = FeignConfig.class,
             fallback = ProductClientFallback.class)
public interface ProductClient {

    @PostMapping("/products/list")
    List<ProductClientResponse> getProductIdIn(@RequestBody List<Long> productIds);
}
