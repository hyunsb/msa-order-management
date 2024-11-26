package com.spring_cloud.eureka.client.order.controller;

import com.spring_cloud.eureka.client.order.dto.OrderRequest;
import com.spring_cloud.eureka.client.order.dto.OrderResponse;
import com.spring_cloud.eureka.client.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/testConnection")
    public String testConnection() {
        return orderService.test();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest,
                                     @RequestHeader(value = "X-User-Id") String userId,
                                     @RequestHeader(value = "X-Role") String role) {

        return orderService.createOrder(orderRequest, userId);
    }
}
