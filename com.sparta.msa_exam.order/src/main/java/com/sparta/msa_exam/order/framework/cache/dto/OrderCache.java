package com.sparta.msa_exam.order.framework.cache.dto;

import com.sparta.msa_exam.order.application.domain.Order;
import com.sparta.msa_exam.order.application.domain.Product;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor
@RedisHash("order")
public class OrderCache implements Serializable {

    private Long id;
    private Long orderedBy;
    private List<ProductCache> orderedProducts;

    @TimeToLive
    private Long timeToLive;

    public OrderCache(Long id, Long orderedBy, List<ProductCache> orderedProducts) {
        this.id = id;
        this.orderedBy = orderedBy;
        this.orderedProducts = orderedProducts;
        timeToLive = 60L;
    }

    public static OrderCache from(Order order) {
        return new OrderCache(
            order.getId(),
            order.getOrderedBy(),
            order.getOrderedProducts().stream().map(ProductCache::from).toList()
        );
    }

    @AllArgsConstructor
    private static class ProductCache implements Serializable {

        private Long id;
        private Integer quantity;

        private static ProductCache from(Product product) {
            return new ProductCache(product.getProductId(), product.getQuantity());
        }

        public Product toDomain() {
            return new Product(id, quantity);
        }
    }

    public Order toDomain() {
        return new Order(
            id, orderedBy, orderedProducts.stream().map(ProductCache::toDomain).toList()
        );
    }
}
