package com.sparta.msa_exam.product.application.domain;

public class Product {

    private Long id;
    private String name;
    private Integer supplyPrice;
    private Long createdBy;

    public Product(Long id, String name, Integer supplyPrice, Long createdBy) {
        this.id = id;
        this.name = name;
        this.supplyPrice = supplyPrice;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getSupplyPrice() {
        return supplyPrice;
    }

    public Long getCreatedBy() {
        return createdBy;
    }
}
