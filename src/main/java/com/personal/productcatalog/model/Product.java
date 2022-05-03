package com.personal.productcatalog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer stock;
    private BigDecimal price;

    public Product() {}

    public Product(String name, Integer stock, BigDecimal price) {
        this.name = name;
        this.stock = stock;
        this.price = price;
    }
}
