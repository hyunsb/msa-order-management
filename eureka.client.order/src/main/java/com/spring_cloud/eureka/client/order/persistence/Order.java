package com.spring_cloud.eureka.client.order.persistence;

import com.spring_cloud.eureka.client.order.dto.OrderResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "order_item_id")
    private List<Long> orderItemIds;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private LocalDateTime deletedAt;
    private String deletedBy;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = OrderStatus.CREATED;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public static Order createOrder(List<Long> orderItemIds, String createdBy) {
        return Order.builder()
            .orderItemIds(orderItemIds)
            .createdBy(createdBy)
            .status(OrderStatus.CREATED)
            .build();
    }

    public void updateOrder(List<Long> orderItemIds, String updatedBy, OrderStatus status) {
        this.orderItemIds = orderItemIds;
        this.updatedBy = updatedBy;
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public void deleteOrder(String deletedBy) {
        this.deletedBy = deletedBy;
        this.deletedAt = LocalDateTime.now();
    }

    public OrderResponse toResponseDto() {
        return new OrderResponse(
            this.id,
            this.status.name(),
            this.createdAt,
            this.createdBy,
            this.updatedAt,
            this.updatedBy,
            this.orderItemIds
        );
    }
}
