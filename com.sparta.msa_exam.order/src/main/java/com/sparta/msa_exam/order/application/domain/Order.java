package com.sparta.msa_exam.order.application.domain;


import java.util.List;

public class Order {

    private Long id;
    private Long orderedBy;
    private List<Product> orderedProducts;

    public Order(Long id, Long orderedBy, List<Product> orderedProducts) {
        this.id = id;
        this.orderedBy = orderedBy;
        this.orderedProducts = orderedProducts;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderedBy() {
        return orderedBy;
    }

    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }
}
