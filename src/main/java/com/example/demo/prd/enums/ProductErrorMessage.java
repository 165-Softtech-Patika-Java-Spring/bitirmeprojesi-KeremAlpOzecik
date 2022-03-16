package com.example.demo.prd.enums;

import com.example.demo.gen.enums.BaseErrorMessage;

public enum ProductErrorMessage implements BaseErrorMessage {

    PRODUCT_ERROR_MESSAGE("Product not found!"),
    ;

    private String message;
    ProductErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
