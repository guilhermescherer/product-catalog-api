package com.personal.productcatalog.dto;

import com.personal.productcatalog.model.Category;
import com.personal.productcatalog.model.Product;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class ProductDTO {

    private final Long id;
    private final String name;
    private final Integer stock;
    private final BigDecimal price;
    private final String description;
    private final Category category;
    private final LocalDate createdDate;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.stock = product.getStock();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.createdDate = product.getCreatedDate();
    }

    public static Page<ProductDTO> toDTO(Page<Product> products) {
        return products.map(ProductDTO::new);
    }
}
