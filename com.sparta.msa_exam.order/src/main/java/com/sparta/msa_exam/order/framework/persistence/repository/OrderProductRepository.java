package com.sparta.msa_exam.order.framework.persistence.repository;

import com.sparta.msa_exam.order.framework.persistence.entity.OrderProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Long> {

    @Query("SELECT op FROM orderProduct op WHERE op.order.id = :orderId")
    List<OrderProductEntity> findAllByOrderId(Long orderId);
}
