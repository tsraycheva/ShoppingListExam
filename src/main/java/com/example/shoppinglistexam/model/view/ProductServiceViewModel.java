package com.example.shoppinglistexam.model.view;

import java.math.BigDecimal;

public class ProductServiceViewModel {

    private Long id;
    private String name;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public ProductServiceViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductServiceViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductServiceViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
