package com.sparta.msa_exam.product.bootstrap.rest.dto;

import com.sparta.msa_exam.product.application.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductListResponse {

    private Long id;
    private String name;
    private Integer supplyPrice;

    public static ProductListResponse from(Product product) {
        return new ProductListResponse(
            product.getId(), product.getName(), product.getSupplyPrice()
        );
    }
}
