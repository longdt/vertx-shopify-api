package com.github.longdt.shopify.util;

import com.github.longdt.shopify.exception.ShopifyException;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;

import java.util.function.Function;

public class HttpResponseTransformer<T> implements Function<HttpResponse<Buffer>, T> {
    private final int expectedStatus;
    private final Class<T> responseType;

    public HttpResponseTransformer(int expectedStatus, Class<T> responseType) {
        this.expectedStatus = expectedStatus;
        this.responseType = responseType;
    }

    @Override
    public T apply(HttpResponse<Buffer> response) {
        if (response.statusCode() == expectedStatus) {
            return Json.decodeValue(response.body(), responseType);
        } else {
           throw new ShopifyException(response.statusCode() + " " + response.bodyAsString());
        }
    }
}
