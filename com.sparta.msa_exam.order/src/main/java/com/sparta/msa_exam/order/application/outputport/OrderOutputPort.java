package com.sparta.msa_exam.order.application.outputport;

import com.sparta.msa_exam.order.application.domain.OrderForCreate;

public interface OrderOutputPort {

    void save(OrderForCreate orderForCreate);
}
