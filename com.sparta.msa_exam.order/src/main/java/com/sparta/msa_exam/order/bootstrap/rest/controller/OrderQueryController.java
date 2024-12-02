package com.sparta.msa_exam.order.bootstrap.rest.controller;

import com.sparta.msa_exam.order.application.domain.Order;
import com.sparta.msa_exam.order.bootstrap.rest.dto.OrderApiResponse;
import com.sparta.msa_exam.order.bootstrap.rest.dto.OrderApiResponse.Success;
import com.sparta.msa_exam.order.bootstrap.rest.dto.OrderDetailResponse;
import com.sparta.msa_exam.order.framework.persistence.adapter.OrderAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderQueryController {

    private final OrderAdapter orderAdapter;

    @Value("${server.port}")
    private String port;

    @GetMapping("/{id}")
    public ResponseEntity<Success<OrderDetailResponse>> findOne(
        @PathVariable(name = "id") Long id
    ) {
        Order order = orderAdapter.findOne(id);
        OrderDetailResponse response = OrderDetailResponse.from(order);
        return OrderApiResponse.success(response, HttpStatus.OK, port);
    }
}
