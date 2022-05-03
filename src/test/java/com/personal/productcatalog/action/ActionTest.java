package com.personal.productcatalog.action;

import com.personal.productcatalog.fixture.ProductFixture;
import com.personal.productcatalog.form.ProductForm;
import com.personal.productcatalog.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.only;

@ExtendWith(MockitoExtension.class)
public class ActionTest {

    private static final String DEFAULT_NAME = "iPhone";
    private static final Integer DEFAULT_STOCK = 3;
    private static final BigDecimal DEFAULT_PRICE = new BigDecimal("3000");

    @InjectMocks
    private Actions actions;
    @Mock
    private AbstractAction<Product> createProduct;

    @Test
    public void shouldReturnCreatedProduct() {
        ProductForm productForm = new ProductForm();
        productForm.setName(DEFAULT_NAME);
        productForm.setStock(DEFAULT_STOCK);
        productForm.setPrice(DEFAULT_PRICE);

        Product product = ProductFixture.get()
                .withName(DEFAULT_NAME)
                .withStock(DEFAULT_STOCK)
                .withPrice(DEFAULT_PRICE)
                .build();

        given(createProduct.perform(any())).willReturn(product);

        Product saved = actions.createProduct(productForm);

        then(createProduct).should(only()).perform(any());
        assertEquals(product.getName(), saved.getName());
        assertEquals(product.getStock(), saved.getStock());
        assertEquals(product.getPrice(), saved.getPrice());
    }
}
