package com.personal.productcatalog.service;

import com.personal.productcatalog.builder.FilterBuilder;
import com.personal.productcatalog.exception.NotFoundException;
import com.personal.productcatalog.form.ProductForm;
import com.personal.productcatalog.model.Product;
import com.personal.productcatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAll(ProductForm form, Pageable pageable) {
        ProductForm productForm = isNull(form) ? new ProductForm() : form;

        FilterBuilder<ProductForm, Product> builder = new FilterBuilder<>();
        Example<Product> filter = builder.createFilterIgnoringNullValues(productForm, new Product());

        return productRepository.findAll(filter, pageable);
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found product with id " + id));
    }

    public void deleteById(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
