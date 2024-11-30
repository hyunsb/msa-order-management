package com.sparta.msa_exam.product.framework.persistence.repository;

import com.sparta.msa_exam.product.framework.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
