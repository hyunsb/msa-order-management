package com.sparta.msa_exam.order.bootstrap.rest.dto;

import com.sparta.msa_exam.order.application.domain.Order;
import com.sparta.msa_exam.order.application.domain.Product;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {

    private Long id;
    private Long orderedBy;
    private List<ProductResponse> orderedProducts;

    public static OrderDetailResponse from(Order order) {
        return new OrderDetailResponse(
            order.getId(),
            order.getOrderedBy(),
            order.getOrderedProducts().stream().map(ProductResponse::from).toList()
        );
    }

    @Getter
    @AllArgsConstructor
    public static class ProductResponse {

        private Long productId;
        private Integer quantity;

        public static ProductResponse from(Product product) {
            return new ProductResponse(product.getProductId(), product.getQuantity());
        }
    }
}
