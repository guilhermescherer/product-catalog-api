package com.personal.productcatalog.config.bean;

import com.personal.productcatalog.action.AbstractAction;
import com.personal.productcatalog.action.product.SaveProductToDatabase;
import com.personal.productcatalog.action.product.SendEmailProductCreated;
import com.personal.productcatalog.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigActions {

    @Autowired
    private SaveProductToDatabase saveProductToDatabase;
    @Autowired
    private SendEmailProductCreated sendEmailProductCreated;

    @Bean
    public AbstractAction<Product> createProduct() {
        saveProductToDatabase.linkWith(sendEmailProductCreated);
        return saveProductToDatabase;
    }
}
