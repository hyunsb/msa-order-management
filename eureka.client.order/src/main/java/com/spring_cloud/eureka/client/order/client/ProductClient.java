package com.spring_cloud.eureka.client.order.client;

import com.spring_cloud.eureka.client.order.dto.ProductListResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductResponse getProduct(@PathVariable("id") Long id);

    @GetMapping("/products/{id}/reduceQuantity")
    void reduceProductQuantity(@PathVariable("id") Long id, @RequestParam("quantity") int quantity);

    @GetMapping("/products/testConnection")
    String testConnection();

    @GetMapping("/products")
    List<ProductListResponse> getProducts(@RequestBody List<Long> productIds);
}
