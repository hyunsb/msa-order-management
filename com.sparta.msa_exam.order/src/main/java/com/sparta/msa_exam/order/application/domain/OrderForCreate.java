package com.sparta.msa_exam.order.application.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderForCreate {

    private List<Product> orderedProducts;
    private Long orderedBy;

    public OrderForCreate(List<Product> orderedProducts, Long orderedBy) {
        this.orderedProducts = orderedProducts;
        this.orderedBy = orderedBy;
    }

    public List<Long> getOrderedProductIds() {
        return orderedProducts.stream()
            .map(Product::getProductId)
            .toList();
    }

    public boolean equalProductSize(int size) {
        return Objects.equals(orderedProducts.size(), size);
    }

    public List<Long> mismatchedProductIds(List<Long> productIds) {
        List<Long> notMatchedIds = new ArrayList<>(getOrderedProductIds());
        for (Long productId : productIds) {
            if (isContainProduct(productId)) {
                notMatchedIds.remove(productId);
            }
        }
        return notMatchedIds;
    }

    private boolean isContainProduct(Long productId) {
        for (Product orderedProduct : orderedProducts) {
            if (Objects.equals(orderedProduct.getProductId(), productId)) {
                return true;
            }
        }
        return false;
    }

    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public Long getOrderedBy() {
        return orderedBy;
    }
}
