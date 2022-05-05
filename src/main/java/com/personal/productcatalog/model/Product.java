package com.personal.productcatalog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    private LocalDate createdDate;

    public Product() {}

    public Product(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
