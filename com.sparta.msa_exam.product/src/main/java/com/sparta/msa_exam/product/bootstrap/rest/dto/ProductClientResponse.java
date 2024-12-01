package com.sparta.msa_exam.product.bootstrap.rest.dto;

import com.sparta.msa_exam.product.application.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductClientResponse {

    private Long id;
    private String name;

    public static ProductClientResponse from(Product product) {
        return new ProductClientResponse(product.getId(), product.getName());
    }
}