package com.personal.productcatalog.facade;

import com.personal.productcatalog.action.Actions;
import com.personal.productcatalog.annotations.Facade;
import com.personal.productcatalog.form.ProductForm;
import com.personal.productcatalog.model.Product;
import com.personal.productcatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Facade
public class ProductFacade {

    private final ProductService productService;
    private final Actions actions;

    @Autowired
    public ProductFacade(ProductService productService, Actions actions) {
        this.productService = productService;
        this.actions = actions;
    }

    public Page<Product> findAll(ProductForm form, Pageable pageable) {
        return productService.findAll(form, pageable);
    }

    public Product findById(Long id) {
        return productService.findById(id);
    }

    public void deleteById(Long id) {
        productService.deleteById(id);
    }

    public Product saveByForm(ProductForm form) {
        return actions.createProduct(form);
    }

    public void deleteAll() {
        productService.deleteAll();
    }
}
