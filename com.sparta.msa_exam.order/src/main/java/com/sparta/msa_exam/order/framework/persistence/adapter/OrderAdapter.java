package com.sparta.msa_exam.order.framework.persistence.adapter;

import com.sparta.msa_exam.order.application.domain.Order;
import com.sparta.msa_exam.order.application.domain.OrderForCreate;
import com.sparta.msa_exam.order.application.outputport.OrderOutputPort;
import com.sparta.msa_exam.order.framework.cache.dto.OrderCache;
import com.sparta.msa_exam.order.framework.cache.repository.OrderCacheRepository;
import com.sparta.msa_exam.order.framework.feignclient.client.ProductClient;
import com.sparta.msa_exam.order.framework.feignclient.dto.ProductClientResponse;
import com.sparta.msa_exam.order.framework.persistence.entity.OrderEntity;
import com.sparta.msa_exam.order.framework.persistence.entity.OrderProductEntity;
import com.sparta.msa_exam.order.framework.persistence.repository.OrderProductRepository;
import com.sparta.msa_exam.order.framework.persistence.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class OrderAdapter implements OrderOutputPort {

    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final OrderCacheRepository orderCacheRepository;

    @Transactional
    @Override
    public Order save(OrderForCreate orderForCreate) {
        validateOrderedProducts(orderForCreate);

        OrderEntity orderEntity = orderRepository.save(OrderEntity.from(orderForCreate));

        List<OrderProductEntity> orderProducts = orderForCreate.getOrderedProducts().stream()
            .map(product -> OrderProductEntity.from(orderEntity, product))
            .toList();
        orderProducts = orderProductRepository.saveAll(orderProducts);

        Order order = orderEntity.toDomainWith(orderProducts);
        orderCacheRepository.save(OrderCache.from(order));

        return order;
    }

    private void validateOrderedProducts(OrderForCreate orderForCreate) {
        List<Long> productIds = productClient.getProductIdIn(orderForCreate.getOrderedProductIds())
                .stream().map(ProductClientResponse::getId).toList();

        if (!orderForCreate.equalProductSize(productIds.size())) {
            List<Long> mismatchedProductIds = orderForCreate.mismatchedProductIds(productIds);
            throw new IllegalArgumentException("유효하지 않은 상품이 존재합니다. id=" + mismatchedProductIds);
        }
    }

    @Transactional(readOnly = true)
    public Order findOne(Long id) {

        OrderEntity orderEntity = orderRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 주문입니다."));

        List<OrderProductEntity> orderProducts = orderProductRepository.findAllByOrderId(id);

        return orderEntity.toDomainWith(orderProducts);
    }
}
