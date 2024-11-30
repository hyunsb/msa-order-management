package com.sparta.msa_exam.order.bootstrap.rest.controller;

import com.sparta.msa_exam.order.application.domain.OrderForCreate;
import com.sparta.msa_exam.order.application.domain.Product;
import com.sparta.msa_exam.order.application.service.OrderService;
import com.sparta.msa_exam.order.bootstrap.rest.dto.OrderCreation.Request;
import com.sparta.msa_exam.order.bootstrap.rest.util.actorId.ActorId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderCommandController {

    private final OrderService orderService;

    @Value("${server.port}")
    private String port;

    @PostMapping
    public ResponseEntity<?> createOrder(
        @RequestBody List<Request> createOrderRequest,
        @ActorId Long actorId
    ) {
        List<Product> orderedProducts = createOrderRequest.stream().map(Request::toDomain).toList();
        OrderForCreate orderForCreate = new OrderForCreate(orderedProducts, actorId);
        orderService.save(orderForCreate);


        return null;
    }
}
