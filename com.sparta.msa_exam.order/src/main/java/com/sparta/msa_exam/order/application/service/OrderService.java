package com.sparta.msa_exam.order.application.service;

import com.sparta.msa_exam.order.application.domain.OrderForCreate;
import com.sparta.msa_exam.order.application.outputport.OrderOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderOutputPort orderOutputPort;

    public void save(OrderForCreate orderForCreate) {
        orderOutputPort.save(orderForCreate);
    }
}
