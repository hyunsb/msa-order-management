package com.spring_cloud.eureka.client.order.dto;

import com.spring_cloud.eureka.client.order.persistence.Order;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderResponse {

    private Long orderId;
    private String status;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private List<Long> orderItemIds;

    public static OrderResponse from(Order savedOrder) {
        return new OrderResponse(
            savedOrder.getId(),
            savedOrder.getStatus().name(),
            savedOrder.getCreatedAt(),
            savedOrder.getCreatedBy(),
            savedOrder.getUpdatedAt(),
            savedOrder.getUpdatedBy(),
            savedOrder.getOrderItemIds()
        );
    }
}
