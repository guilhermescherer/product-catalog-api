package com.personal.productcatalog.exception;

public class MailException extends RuntimeException {

    public MailException(Exception e) {
        super(e);
    }
}
