package com.github.longdt.shopify.exception;

/**
 * Created by thienlong on 24/06/2016.
 */
public class ShopifyException extends RuntimeException {
    public ShopifyException() {
    }

    public ShopifyException(String message) {
        super(message);
    }
}
