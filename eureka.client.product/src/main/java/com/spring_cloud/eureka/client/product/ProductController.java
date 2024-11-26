package com.spring_cloud.eureka.client.product;

import com.spring_cloud.eureka.client.product.dto.ProductListResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/testConnection")
    public String test() {
        throw new IllegalArgumentException("예외 전파 테스트");
//        return "connect success";
    }

    @GetMapping
    public List<ProductListResponse> getProducts(@RequestBody List<Long> productIds) {
        return productService.findByIds(productIds).stream()
            .map(ProductListResponse::from)
            .toList();
    }
}
