package com.github.longdt.shopify.util;

import com.github.longdt.shopify.exception.ShopifyException;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;

import java.util.function.Function;

public class HttpStatusVerifier implements Function<HttpResponse<Buffer>, Void> {
    public static final HttpStatusVerifier OK = new HttpStatusVerifier(200);
    public static final HttpStatusVerifier NO_CONTENT = new HttpStatusVerifier(204);

    private final int expectedStatus;

    public HttpStatusVerifier(int expectedStatus) {
        this.expectedStatus = expectedStatus;
    }

    @Override
    public Void apply(HttpResponse<Buffer> response) {
        if (response.statusCode() == expectedStatus) {
            return null;
        } else {
            throw new ShopifyException(response.statusCode() + " " + response.bodyAsString());
        }
    }
}
