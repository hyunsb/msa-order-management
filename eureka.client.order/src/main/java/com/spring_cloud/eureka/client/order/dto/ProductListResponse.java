package com.spring_cloud.eureka.client.order.dto;

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
}
