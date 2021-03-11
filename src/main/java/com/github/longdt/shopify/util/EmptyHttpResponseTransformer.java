package com.github.longdt.shopify.util;

import com.github.longdt.shopify.exception.ShopifyException;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;

import java.util.function.Function;

public class EmptyHttpResponseTransformer implements Function<HttpResponse<Buffer>, Void> {
    public static final EmptyHttpResponseTransformer OK = new EmptyHttpResponseTransformer(200);
    private final int successHttpStatus;

    public EmptyHttpResponseTransformer(int successHttpStatus) {
        this.successHttpStatus = successHttpStatus;
    }

    @Override
    public Void apply(HttpResponse<Buffer> response) {
        if (response.statusCode() == successHttpStatus) {
            return null;
        } else {
            throw new ShopifyException(response.statusCode() + " " + response.bodyAsString());
        }
    }
}
