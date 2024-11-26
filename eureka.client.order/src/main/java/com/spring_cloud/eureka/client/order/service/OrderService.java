package com.spring_cloud.eureka.client.order.service;

import com.spring_cloud.eureka.client.order.client.ProductClient;
import com.spring_cloud.eureka.client.order.dto.OrderRequest;
import com.spring_cloud.eureka.client.order.dto.OrderResponse;
import com.spring_cloud.eureka.client.order.dto.ProductListResponse;
import com.spring_cloud.eureka.client.order.persistence.Order;
import com.spring_cloud.eureka.client.order.persistence.OrderRepository;
import java.util.IllformedLocaleException;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest, String userId) {

        List<Long> orderItemIds = orderRequest.getOrderItemIds();

        List<ProductListResponse> products = productClient.getProducts(orderItemIds);
        validateAvailableOrder(orderItemIds, products);

        for (Long productId : orderRequest.getOrderItemIds()) {
            productClient.reduceProductQuantity(productId, 1);
        }

        Order order = Order.createOrder(orderRequest.getOrderItemIds(), userId);
        Order savedOrder = orderRepository.save(order);
        return OrderResponse.from(savedOrder);
    }

    private void validateAvailableOrder(List<Long> orderItemIds, List<ProductListResponse> products) {

        if (!Objects.equals(orderItemIds.size(), products.size())) {
            throw new IllegalArgumentException("유효하지 않은 상품이 포함되어 있습니다.");
        }

        for (ProductListResponse product : products) {
            if (product.getQuantity() < 1) {
                throw new IllegalArgumentException(product.getName() + " 상품 수량이 부족하여 주문이 불가능합니다.");
            }
        }
    }


    public String test() {
        String response = productClient.testConnection();
        log.info("response = " + response);

        return response;
    }
}
