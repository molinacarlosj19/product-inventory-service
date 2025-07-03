package com.carlosmolina.productinventoryservice.exception;

public class ResourceNotFoundException  extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
