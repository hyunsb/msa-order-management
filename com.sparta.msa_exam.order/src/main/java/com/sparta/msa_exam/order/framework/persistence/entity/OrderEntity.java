package com.sparta.msa_exam.order.framework.persistence.entity;

import com.sparta.msa_exam.order.application.domain.OrderForCreate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
}
