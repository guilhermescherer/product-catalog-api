package com.personal.productcatalog.service;

import com.personal.productcatalog.exception.NotFoundException;
import com.personal.productcatalog.fixture.ProductFixture;
import com.personal.productcatalog.model.Product;
import com.personal.productcatalog.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.only;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private static final int LIST_SIZE = 5;
    private static final long NON_EXISTENT_ID = 9999999999L;

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;

    @Test
    public void shouldReturnAllProducts() {
        Pageable page = PageRequest.of(0, 10);

        given(productRepository.findAll(any(), eq(page))).willReturn(ProductFixture.get().buildRandomPage(LIST_SIZE));

        Page<Product> products = productService.findAll(null, page);

        then(productRepository).should(only()).findAll(any(), eq(page));
        assertEquals(LIST_SIZE, products.getTotalElements());
    }

    @Test
    public void shouldReturnProductById() {
        Product product = ProductFixture.get().buildRandom();
        given(productRepository.findById(product.getId())).willReturn(Optional.of(product));

        Product received = productService.findById(product.getId());

        then(productRepository).should(only()).findById(product.getId());
        assertEquals(product, received);
    }

    @Test
    public void shouldThrowExceptionWhenProductNotExistingInRepository() {
        given(productRepository.findById(NON_EXISTENT_ID)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productService.findById(NON_EXISTENT_ID));
        then(productRepository).should(only()).findById(NON_EXISTENT_ID);
    }

    @Test
    public void shouldSaveProduct() {
        Product product = ProductFixture.get().buildRandom();

        given(productRepository.save(product)).willReturn(product);

        Product productSaved = productService.save(product);

        then(productRepository).should(only()).save(product);
        assertEquals(product, productSaved);
    }
}
