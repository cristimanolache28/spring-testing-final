package com.endava.restaurant.model;

public class Error {

    private Integer customCode;

    private String message;

    public Error customCode(Integer customCode) {
        this.customCode = customCode;
        return this;
    }

    public Error message(String message) {
        this.message = message;
        return this;
    }
}
