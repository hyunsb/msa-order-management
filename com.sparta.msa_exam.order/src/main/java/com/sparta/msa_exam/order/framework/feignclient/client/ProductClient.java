package com.sparta.msa_exam.order.framework.feignclient.client;

import com.sparta.msa_exam.order.framework.feignclient.dto.ProductClientResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service")
public interface ProductClient {

    @PostMapping("/products/list")
    List<ProductClientResponse> getProductIdIn(@RequestBody List<Long> productIds);
}
