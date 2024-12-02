package com.sparta.msa_exam.order.framework.cache.adapter;

import com.sparta.msa_exam.order.application.domain.Order;
import com.sparta.msa_exam.order.framework.cache.dto.OrderCache;
import com.sparta.msa_exam.order.framework.cache.exception.CacheMissException;
import com.sparta.msa_exam.order.framework.cache.repository.OrderCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderCacheAdapter {

    private final OrderCacheRepository orderCacheRepository;

    public Order findOne(Long id) {
        return orderCacheRepository.findById(id)
            .orElseThrow(CacheMissException::new)
            .toDomain();
    }

    public Order save(Order order) {
        OrderCache orderCache = OrderCache.from(order);
        orderCacheRepository.save(orderCache);
        return order;
    }
}
