package com.victoraster.StoreMananger.exceptions;

public class InvalidFields extends RuntimeException {
    public InvalidFields() {
        super();
    }

    public InvalidFields(String message) {
        super(message);
    }
}
