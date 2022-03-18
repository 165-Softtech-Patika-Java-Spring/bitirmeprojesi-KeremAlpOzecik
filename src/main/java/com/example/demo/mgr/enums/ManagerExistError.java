package com.example.demo.mgr.enums;

import com.example.demo.gen.enums.BaseErrorMessage;

public enum ManagerExistError implements BaseErrorMessage {

    MANAGER_EXIST_ERROR("There is a manager registered with this username, try another username"),
    ;

    private String message;
    ManagerExistError(String message) {
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
