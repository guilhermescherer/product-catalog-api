package com.personal.productcatalog.dto;

import com.personal.productcatalog.model.Category;
import com.personal.productcatalog.model.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private Integer stock;
    private BigDecimal price;
    private String description;
    private Category category;
    private LocalDate createdDate;
    private String image;

    public ProductDTO(Product product) {
        BeanUtils.copyProperties(product, this);
    }

    public static Page<ProductDTO> toDTO(Page<Product> products) {
        return products.map(ProductDTO::new);
    }
}
