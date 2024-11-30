package com.sparta.msa_exam.product.bootstrap.rest.dto;

import com.sparta.msa_exam.product.application.domain.Product;
import com.sparta.msa_exam.product.application.domain.ProductForCreate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductCreation {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private String name;
        private Integer supplyPrice;

        public ProductForCreate toDomain(Long createdBy) {
            return new ProductForCreate(name, supplyPrice, createdBy);
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long id;
        private String name;
        private Integer supplyPrice;
        private Long createdBy;

        public static Response from(Product product) {
            return new Response(
                product.getId(),
                product.getName(),
                product.getSupplyPrice(),
                product.getCreatedBy()
            );
        }
    }
}
