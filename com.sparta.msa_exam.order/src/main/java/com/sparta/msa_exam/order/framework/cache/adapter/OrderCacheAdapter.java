package com.sparta.msa_exam.order.framework.cache.adapter;

import com.sparta.msa_exam.order.application.domain.Order;
import com.sparta.msa_exam.order.framework.cache.dto.OrderCache;
import com.sparta.msa_exam.order.framework.cache.repository.OrderCacheRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderCacheAdapter {

    private final OrderCacheRepository orderCacheRepository;

    public Optional<Order> findOne(Long id) {
        return orderCacheRepository.findById(id)
            .map(OrderCache::toDomain)
            .or(Optional::empty);
    }

    public Order save(Order order) {
        OrderCache orderCache = OrderCache.from(order);
        orderCacheRepository.save(orderCache);
        return order;
    }
}
