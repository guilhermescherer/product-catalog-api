package com.personal.productcatalog.fixture;

import com.personal.productcatalog.model.Product;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProductFixture {

    private Product product;

    public ProductFixture() {
        this.product = new Product();
    }

    public static ProductFixture get() {
        return new ProductFixture();
    }

    public Product build() {
        return product;
    }

    public Product buildRandom() {
        return this.random().build();
    }

    public List<Product> buildRandomList(int quantity) {
        return IntStream
                .range(0, quantity)
                .mapToObj(x -> ProductFixture.get().buildRandom())
                .collect(Collectors.toList());
    }

    public Page<Product> buildRandomPage(int quantity) {
        List<Product> products = buildRandomList(quantity);
        return new PageImpl<>(products);
    }

    private ProductFixture random() {
        return this
                .withId(RandomUtils.nextLong())
                .withName(RandomStringUtils.random(5))
                .withStock(RandomUtils.nextInt(0, Integer.MAX_VALUE))
                .withPrice(new BigDecimal(RandomStringUtils.randomNumeric(3)));
    }

    public ProductFixture withPrice(BigDecimal price) {
        product.setPrice(price);
        return this;
    }

    public ProductFixture withStock(Integer stock) {
        product.setStock(stock);
        return this;
    }

    public ProductFixture withName(String name) {
        product.setName(name);
        return this;
    }

    public ProductFixture withId(Long id) {
        product.setId(id);
        return this;
    }
}
