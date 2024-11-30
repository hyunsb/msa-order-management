package com.sparta.msa_exam.order.bootstrap.rest.dto;

import com.sparta.msa_exam.order.application.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class OrderCreation {

    @ToString
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private Long productId;
        private Integer quantity;

        public Product toDomain() {
            return new Product(productId, quantity);
        }
    }
}
