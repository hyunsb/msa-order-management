package com.sparta.msa_exam.order.application.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Product {

    private Long productId;
    private Integer quantity;

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}