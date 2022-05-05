package com.personal.productcatalog.form;

import com.personal.productcatalog.model.Category;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
public class ProductForm {

    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @Min(value = 0)
    private Integer stock;
    @NotNull
    @Min(value = 0)
    private BigDecimal price;
    @NotNull
    @NotEmpty
    private String description;
    @NotNull
    private Category category;
}
