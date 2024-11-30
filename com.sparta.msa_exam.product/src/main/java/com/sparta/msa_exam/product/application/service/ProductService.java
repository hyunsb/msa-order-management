package com.sparta.msa_exam.product.application.service;

import com.sparta.msa_exam.product.application.domain.Product;
import com.sparta.msa_exam.product.application.domain.ProductForCreate;
import com.sparta.msa_exam.product.application.outputport.ProductOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductOutputPort productOutputPort;

    public Product create(ProductForCreate productForCreate) {
        return productOutputPort.saveOne(productForCreate);
    }
}
