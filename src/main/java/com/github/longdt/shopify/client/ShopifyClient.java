package com.github.longdt.shopify.client;

import com.github.longdt.shopify.model.OAuthToken;
import com.github.longdt.shopify.util.Futures;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.WebClient;

import java.util.Map;

public interface ShopifyClient {
    Future<OAuthToken> requestToken(String shop, String code);

    boolean verifyRequest(String hmac, Map<String, String[]> params);

    boolean verifyRequest(String hmac, MultiMap params);

    boolean verifyData(String hmac, String data);

    boolean verifyData(String hmac, Buffer data);

    String getInstallUrl(String shop, String scopes, String redirectUri);

    Session newSession(String shop, String token);

    WebClient getWebClient();
}
