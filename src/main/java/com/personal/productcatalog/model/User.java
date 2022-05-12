package com.personal.productcatalog.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {

    private String name;
    private String email;
    private List<String> authorities;
}
