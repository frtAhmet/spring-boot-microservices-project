package com.frtahmet.bookstore.catalog.domain;

public class ProductNotFoundException extends RuntimeException {

    ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException withCode(String code) {
        return new ProductNotFoundException("Product with code " + code + " not found.");
    }
}
