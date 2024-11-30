package com.sparta.msa_exam.order.framework.persistence.repository;

import com.sparta.msa_exam.order.framework.persistence.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Long> {

}
