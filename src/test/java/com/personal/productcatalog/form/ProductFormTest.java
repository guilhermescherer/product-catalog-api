package com.personal.productcatalog.form;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

import static com.personal.productcatalog.utils.MessageUtils.NOT_NULL;

public class ProductFormTest {
    private static final String NOT_EMPTY = "must not be empty";
    private static final String GREATER_ZERO = "must be greater than or equal to 0";

    private static Validator validator;

    private ProductForm productForm;

    @BeforeAll
    public static void beforeAll() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    public void beforeEach() {
        productForm = new ProductForm();
    }

    @Test
    public void shouldNotAcceptNullName() {
        Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm);
        assertViolation(violations, "name", NOT_NULL);
    }

    @Test
    public void shouldNotAcceptNullStock() {
        Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm);
        assertViolation(violations, "stock", NOT_NULL);
    }

    @Test
    public void shouldNotAcceptNullPrice() {
        Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm);
        assertViolation(violations, "price", NOT_NULL);
    }

    @Test
    public void shouldNotAcceptNullDescription() {
        Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm);
        assertViolation(violations, "description", NOT_NULL);
    }

    @Test
    public void shouldNotAcceptNullCategory() {
        Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm);
        assertViolation(violations, "category", NOT_NULL);
    }

    @Test
    public void shouldNotAcceptNullImage() {
        Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm);
        assertViolation(violations, "image", NOT_NULL);
    }

    @Test
    public void shouldNotAcceptEmptyName() {
        productForm.setName("");

        Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm);
        assertViolation(violations, "name", NOT_EMPTY);
    }

    @Test
    public void shouldNotAcceptEmptyDescription() {
        productForm.setDescription("");

        Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm);
        assertViolation(violations, "description", NOT_EMPTY);
    }

    @Test
    public void shouldNotAcceptEmptyImage() {
        Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm);
        assertViolation(violations, "image", NOT_EMPTY);
    }

    @Test
    public void shouldNotAcceptNegativeStock() {
        productForm.setStock(-1);

        Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm);
        assertViolation(violations, "stock", GREATER_ZERO);
    }

    @Test
    public void shouldNotAcceptNegativePrice() {
        productForm.setPrice(new BigDecimal("-1"));

        Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm);
        assertViolation(violations, "price", GREATER_ZERO);
    }

    private static void assertViolation(Set<ConstraintViolation<ProductForm>> violations, String property, String expectedMessage) {
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals(property)
                        && v.getMessage().equals(expectedMessage)));
    }
}
