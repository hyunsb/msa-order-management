package com.sparta.msa_exam.product.bootstrap.rest.controller;

import com.sparta.msa_exam.product.application.domain.Product;
import com.sparta.msa_exam.product.bootstrap.rest.dto.ProductApiResponse;
import com.sparta.msa_exam.product.bootstrap.rest.dto.ProductApiResponse.Success;
import com.sparta.msa_exam.product.bootstrap.rest.dto.ProductListResponse;
import com.sparta.msa_exam.product.framework.persistence.adapter.ProductAdapter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
