package com.sparta.msa_exam.order.framework.persistence.entity;

import com.sparta.msa_exam.order.application.domain.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orderProduct")
public class OrderProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity order;

    private Long productId;

    private Integer quantity;

    public static OrderProductEntity from(OrderEntity order, Product product) {
        return new OrderProductEntity(
            null, order, product.getProductId(), product.getQuantity()
        );
    }
}
