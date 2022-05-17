package com.personal.productcatalog.controller;

import com.personal.productcatalog.dto.ProductDTO;
import com.personal.productcatalog.facade.ProductFacade;
import com.personal.productcatalog.form.ProductForm;
import com.personal.productcatalog.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

import static com.personal.productcatalog.utils.SecurityUtils.ROLE_ADMIN;
import static com.personal.productcatalog.utils.SecurityUtils.ROLE_USER;

@Api(tags = "Product")
@RestController
@RequestMapping("/product")
@Secured({ROLE_USER, ROLE_ADMIN})
@CrossOrigin("*")
public class ProductController {

    private final ProductFacade productFacade;

    @Autowired
    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping
    @ApiOperation(value = "Find all products by a ProductForm filter and pageable data")
    public Page<ProductDTO> findAll(@RequestBody(required = false) ProductForm form, Pageable pageable) {
        Page<Product> products = productFacade.findAll(form, pageable);
        return ProductDTO.toDTO(products);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find product by id")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        Product product = productFacade.findById(id);
        return ResponseEntity.ok(new ProductDTO(product));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ApiOperation(value = "Delete product by id")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        productFacade.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Transactional
    @ApiOperation(value = "Delete all products")
    @Secured(ROLE_ADMIN)
    public  ResponseEntity<?> deleteAll() {
        productFacade.deleteAll();
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @Transactional
    @ApiOperation(value = "Save a new product by ProductForm")
    public ResponseEntity<ProductDTO> saveByForm(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder) {
        Product product = productFacade.saveByForm(form);

        URI uri = uriBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDTO(product));
    }
}
