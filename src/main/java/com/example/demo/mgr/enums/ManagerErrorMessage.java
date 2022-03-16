package com.example.demo.mgr.enums;

import com.example.demo.gen.enums.BaseErrorMessage;

public enum ManagerErrorMessage implements BaseErrorMessage {

   MANAGER_ERROR_MESSAGE("Customer not found!"),
    ;

    private String message;
    ManagerErrorMessage(String message) {
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
