package com.sparta.msa_exam.product.framework.cache.dto;

import com.sparta.msa_exam.product.application.domain.Product;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor
@RedisHash("product")
public class ProductCache implements Serializable {

    private Long id;
    private String name;
    private Integer supplyPrice;
    private Long createdBy;

    @TimeToLive
    private Long timeToLive;

    public ProductCache(Long id, String name, Integer supplyPrice, Long createdBy) {
        this.id = id;
        this.name = name;
        this.supplyPrice = supplyPrice;
        this.createdBy = createdBy;
        timeToLive = 60L;
    }

    public static ProductCache from(Product product) {
        return new ProductCache(
            product.getId(), product.getName(), product.getSupplyPrice(), product.getCreatedBy()
        );
    }

    public Product toDomain() {
        return new Product(id, name, supplyPrice, createdBy);
    }
}
