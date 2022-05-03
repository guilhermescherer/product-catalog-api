package com.personal.productcatalog.dto;

import com.personal.productcatalog.model.Product;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

@Getter
public class ProductDTO {

    private final Long id;
    private final String name;
    private final Integer stock;
    private final BigDecimal price;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.stock = product.getStock();
        this.price = product.getPrice();
    }

    public static Page<ProductDTO> toDTO(Page<Product> products) {
        return products.map(ProductDTO::new);
    }
}
