package com.sparta.msa_exam.product.framework.persistence.entity;

import com.sparta.msa_exam.product.application.domain.Product;
import com.sparta.msa_exam.product.application.domain.ProductForCreate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer supplyPrice;
    private Long createdBy;

    public static ProductEntity from(ProductForCreate productForCreate) {
        return new ProductEntity(
            null,
            productForCreate.name(),
            productForCreate.supplyPrice(),
            productForCreate.createdBy()
        );
    }

    public Product toDomain() {
        return new Product(id, name, supplyPrice, createdBy);
    }
}
