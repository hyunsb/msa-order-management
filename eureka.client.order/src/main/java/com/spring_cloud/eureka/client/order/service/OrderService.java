package com.spring_cloud.eureka.client.order.service;

import com.spring_cloud.eureka.client.order.client.ProductClient;
import com.spring_cloud.eureka.client.order.client.ProductResponse;
import com.spring_cloud.eureka.client.order.dto.OrderRequest;
import com.spring_cloud.eureka.client.order.dto.OrderResponse;
import com.spring_cloud.eureka.client.order.persistence.Order;
import com.spring_cloud.eureka.client.order.persistence.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest, String userId) {
        log.info("orderRequest = " + orderRequest);
        log.info("userId = " + userId);

        String response = productClient.testConnection();
        System.out.println("response = " + response);
        return null;
//        for (Long productId : orderRequest.getOrderItemIds()) {
//            ProductResponse product = productClient.getProduct(productId);
//            log.info("############################ Product 수량 확인 : " + product.getQuantity());
//            if (product.getQuantity() < 1) {
//                throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST, "Product with ID " + productId + " is out of stock.");
//            }
//        }
//
//        // Reduce the quantity of each product by 1
//        for (Long productId : orderRequest.getOrderItemIds()) {
//            productClient.reduceProductQuantity(productId, 1);
//        }
//
//        Order order = Order.createOrder(orderRequest.getOrderItemIds(), userId);
//        Order savedOrder = orderRepository.save(order);
//        return OrderResponse.from(savedOrder);
    }

    public String test() {
        String response = productClient.testConnection();
        log.info("response = " + response);

        return response;
    }
}
