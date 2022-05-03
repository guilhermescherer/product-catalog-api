package com.personal.productcatalog.fixture;

import com.personal.productcatalog.model.User;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UserFixture {

    private static final String VALID_EMAIL = "guilherme_scherer@outlook.com";

    private User user;

    public UserFixture() {
        this.user = new User();
    }

    public static UserFixture get() {
        return new UserFixture();
    }

    public User build() {
        return user;
    }

    public User buildRandom() {
        return this.random().build();
    }

    public List<User> buildRandomList(int quantity) {
        return IntStream
                .range(0, quantity)
                .mapToObj(x -> UserFixture.get().buildRandom())
                .collect(Collectors.toList());
    }

    private UserFixture random() {
        return this
                .withName(RandomStringUtils.random(5))
                .withEmail(VALID_EMAIL);
    }

    public UserFixture withName(String name) {
        user.setName(name);
        return this;
    }

    public UserFixture withEmail(String email) {
        user.setEmail(email);
        return this;
    }
}
