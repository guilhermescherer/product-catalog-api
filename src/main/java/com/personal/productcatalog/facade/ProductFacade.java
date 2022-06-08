package com.personal.productcatalog.facade;

import com.personal.productcatalog.action.product.ProductActions;
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
    private final ProductActions productActions;

    @Autowired
    public ProductFacade(ProductService productService, ProductActions productActions) {
        this.productService = productService;
        this.productActions = productActions;
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
        return productActions.createProduct(form);
    }

    public void deleteAll() {
        productService.deleteAll();
    }
}
