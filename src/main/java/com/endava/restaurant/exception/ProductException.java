package com.endava.restaurant.exception;

import java.text.MessageFormat;

public class ProductException extends RuntimeException {

    public ProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductException(String message) {
        super(message);
    }


    public ProductException(String message, String... arguments) {
        super(MessageFormat.format(message, arguments));
    }
}
