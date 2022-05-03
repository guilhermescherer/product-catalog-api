package com.personal.productcatalog.builder;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

public class FilterBuilder<E, T> {

    public Example<T> createFilterIgnoringNullValues(E form, T object) {
        BeanUtils.copyProperties(form, object);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();

        return Example.of(object, matcher);
    }
}
