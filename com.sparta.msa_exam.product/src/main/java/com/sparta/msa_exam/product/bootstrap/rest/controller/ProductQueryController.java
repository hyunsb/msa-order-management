package com.sparta.msa_exam.product.bootstrap.rest.controller;

import com.sparta.msa_exam.product.application.domain.Product;
import com.sparta.msa_exam.product.bootstrap.rest.dto.ProductApiResponse;
import com.sparta.msa_exam.product.bootstrap.rest.dto.ProductApiResponse.Success;
import com.sparta.msa_exam.product.bootstrap.rest.dto.ProductClientResponse;
import com.sparta.msa_exam.product.bootstrap.rest.dto.ProductListResponse;
import com.sparta.msa_exam.product.framework.persistence.adapter.ProductAdapter;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductQueryController {

    private final ProductAdapter productAdapter;

    @Value("${server.port}")
    private Integer port;

    @GetMapping
    public ResponseEntity<Success<List<ProductListResponse>>> findAll() {
        List<Product> products = productAdapter.findAll();

        List<ProductListResponse> productListResponses =
            products.stream().map(ProductListResponse::from).toList();

        return ProductApiResponse.success(productListResponses, HttpStatus.OK, port);
    }

    // 추후 FindAll에서 동적쿼리로 search 기능 추가하면 FindAll을 사용하고 해당 메서드는 삭제할 것
    @GetMapping("/list")
    public List<ProductClientResponse> getProductIdIn(@RequestBody List<Long> productIds) {
        return productAdapter.findByIdIn(productIds).stream()
            .map(ProductClientResponse::from)
            .toList();
    }
}
