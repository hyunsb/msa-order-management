package com.sparta.msa_exam.order.framework.feignclient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductClientResponse {

    private Long id;
    private String name;
}
