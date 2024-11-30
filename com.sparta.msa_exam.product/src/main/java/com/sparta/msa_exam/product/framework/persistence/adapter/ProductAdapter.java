package com.sparta.msa_exam.product.framework.persistence.adapter;

import com.sparta.msa_exam.product.application.domain.Product;
import com.sparta.msa_exam.product.application.domain.ProductForCreate;
import com.sparta.msa_exam.product.application.outputport.ProductOutputPort;
import com.sparta.msa_exam.product.framework.persistence.entity.ProductEntity;
import com.sparta.msa_exam.product.framework.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductAdapter implements ProductOutputPort {

    private final ProductRepository productRepository;

    @Override
    public Product saveOne(ProductForCreate productForCreate) {
        ProductEntity productEntity = ProductEntity.from(productForCreate);
        productEntity = productRepository.save(productEntity);

        return productEntity.toDomain();
    }
}
