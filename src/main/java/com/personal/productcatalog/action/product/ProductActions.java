package com.personal.productcatalog.action.product;

import com.personal.productcatalog.action.AbstractAction;
import com.personal.productcatalog.form.ProductForm;
import com.personal.productcatalog.model.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static java.time.LocalDate.now;

@Component
public class ProductActions {
    private final AbstractAction<Product> createProduct;

    @Autowired
    public ProductActions(@Qualifier("createProduct") AbstractAction<Product> createProduct) {
        this.createProduct = createProduct;
    }

    @Transactional(rollbackOn = Exception.class)
    public Product createProduct(ProductForm form) {
        Product product = new Product(now());
        BeanUtils.copyProperties(form, product);

        return createProduct.perform(product);
    }
}
