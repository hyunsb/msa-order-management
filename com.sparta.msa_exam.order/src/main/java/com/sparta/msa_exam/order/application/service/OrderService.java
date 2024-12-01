package com.sparta.msa_exam.order.application.service;

import com.sparta.msa_exam.order.application.domain.Order;
import com.sparta.msa_exam.order.application.domain.OrderForCreate;
import com.sparta.msa_exam.order.application.outputport.OrderOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderOutputPort orderOutputPort;

    public Order save(OrderForCreate orderForCreate) {
        return orderOutputPort.save(orderForCreate);
    }
}
