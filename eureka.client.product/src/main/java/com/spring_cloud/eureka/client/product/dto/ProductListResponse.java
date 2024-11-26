package com.spring_cloud.eureka.client.product.dto;


import com.spring_cloud.eureka.client.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductListResponse {

    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Integer quantity;

    public static ProductListResponse from(Product product) {
        return new ProductListResponse(
            product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity()
        );
    }
}
