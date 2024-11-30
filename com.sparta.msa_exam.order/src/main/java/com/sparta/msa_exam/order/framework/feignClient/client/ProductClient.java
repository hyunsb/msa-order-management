package com.sparta.msa_exam.order.framework.feignClient.client;

import com.sparta.msa_exam.order.framework.feignClient.dto.ProductClientResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/list")
    List<ProductClientResponse> getProductIdIn(@RequestBody List<Long> productIds);
}
