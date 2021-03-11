package com.github.longdt.shopify.util;

import com.github.longdt.shopify.exception.ShopifyException;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;

import java.util.function.Function;

public class HttpResponseTransformer<T> implements Function<HttpResponse<Buffer>, T> {
    private final int successHttpStatus;
    private final Class<T> responseType;

    public HttpResponseTransformer(int successHttpStatus, Class<T> responseType) {
        this.successHttpStatus = successHttpStatus;
        this.responseType = responseType;
    }

    @Override
    public T apply(HttpResponse<Buffer> response) {
        if (response.statusCode() == successHttpStatus) {
            return Json.decodeValue(response.body(), responseType);
        } else {
           throw new ShopifyException(response.statusCode() + " " + response.bodyAsString());
        }
    }
}
