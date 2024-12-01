package com.sparta.msa_exam.order.framework.persistence.entity;

import com.sparta.msa_exam.order.application.domain.Order;
import com.sparta.msa_exam.order.application.domain.OrderForCreate;
import com.sparta.msa_exam.order.application.domain.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderedBy;

    public static OrderEntity from(OrderForCreate order) {
        return new OrderEntity(null, order.getOrderedBy());
    }

    public Order toDomainWith(List<OrderProductEntity> orderProductEntities) {
        List<Product> orderedProducts = orderProductEntities.stream()
            .map(OrderProductEntity::toDomain)
            .toList();

        return new Order(id, orderedBy, orderedProducts);
    }
}
