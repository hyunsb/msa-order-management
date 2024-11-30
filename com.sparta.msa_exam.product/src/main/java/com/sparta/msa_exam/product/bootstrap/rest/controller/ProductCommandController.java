package com.sparta.msa_exam.product.bootstrap.rest.controller;

import com.sparta.msa_exam.product.application.domain.Product;
import com.sparta.msa_exam.product.application.service.ProductService;
import com.sparta.msa_exam.product.bootstrap.rest.dto.ProductApiResponse;
import com.sparta.msa_exam.product.bootstrap.rest.dto.ProductApiResponse.Success;
import com.sparta.msa_exam.product.bootstrap.rest.dto.ProductCreation.Request;
import com.sparta.msa_exam.product.bootstrap.rest.dto.ProductCreation.Response;
import com.sparta.msa_exam.product.bootstrap.rest.util.actorId.ActorId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductCommandController {

    @Value("${server.port}")
    private Integer port;

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Success<Response>> createProduct(
        @RequestBody Request createRequest,
        @ActorId Long actorId
    ) {
        Product product = productService.create(createRequest.toDomain(actorId));
        Response response = Response.from(product);

        return ProductApiResponse.success(response, HttpStatus.CREATED, port);
    }
}
