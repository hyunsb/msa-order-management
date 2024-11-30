package com.sparta.msa_exam.order.framework.persistence.repository;

import com.sparta.msa_exam.order.framework.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
