package com.example.tire_warehouse.exception;

public class NotFoundByVendorCodeException extends RuntimeException {

    public NotFoundByVendorCodeException(String message) {
        super(message);
    }
}
