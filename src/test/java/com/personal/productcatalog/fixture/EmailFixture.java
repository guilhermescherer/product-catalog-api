package com.personal.productcatalog.fixture;

import com.personal.productcatalog.model.Email;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class EmailFixture {

    private Email email;

    public static EmailFixture get() {
        return new EmailFixture();
    }

    public Email buildRandom() {
        return this.random().build();
    }

    private Email build() {
        return email;
    }

    private EmailFixture random() {
        String recipient = String.format("%s@email.com", RandomStringUtils.random(5));
        String subject = RandomStringUtils.random(10);
        String body = RandomStringUtils.random(50);

        email = new Email(recipient, subject, body);

        return this;
    }
}
