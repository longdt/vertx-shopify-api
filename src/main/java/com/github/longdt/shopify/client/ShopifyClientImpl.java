package com.github.longdt.shopify.client;

import com.github.longdt.shopify.model.OAuthToken;
import com.github.longdt.shopify.util.HttpResponseTransformer;
import com.github.longdt.shopify.util.Json;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import io.vertx.core.*;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.WebClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

public class ShopifyClientImpl implements ShopifyClient {
    private static final HttpResponseTransformer<OAuthToken> OAUTH_TOKEN_TRANSFORMER =
            new HttpResponseTransformer<>(200, OAuthToken.class);
    private final WebClient webClient;
    private final String key;
    private final String secret;
    private final HashFunction hashFunction;

    public ShopifyClientImpl(Vertx vertx, String key, String secret) {
        this.webClient = WebClient.create(vertx);
        this.key = key;
        this.secret = secret;
        hashFunction = Hashing.hmacSha256(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Future<OAuthToken> requestToken(String shop, String code) {
        Buffer body = Buffer.buffer("{\"client_id\":");
        Json.encode(key, body);
        body.appendString(",\"client_secret\":");
        Json.encode(secret, body);
        body.appendString(",\"code\":");
        Json.encode(code, body);
        body.appendString("}");
        return webClient.post(443, shop, "/admin/oauth/access_token")
                .ssl(true)
                .putHeader("Content-Type", "application/json")
                .sendBuffer(body)
                .map(OAUTH_TOKEN_TRANSFORMER);
    }

    @Override
    public boolean verifyRequest(String hmac, Map<String, String[]> params) {
        StringBuilder query = params.entrySet().stream()
                .filter(e -> {
                    String k = e.getKey();
                    return !k.equals("hmac") && !k.equals("signature");
                })
                .sorted(Map.Entry.comparingByKey()).collect(StringBuilder::new, (r, e) ->
                                r.append(e.getKey()).append('=').append(e.getValue()[0]).append('&')
                        , StringBuilder::append);
        if (query.length() > 0) {
            query.deleteCharAt(query.length() - 1);
        }
        String digest = hashFunction.hashUnencodedChars(query).toString();
        return digest.equals(hmac);
    }

    @Override
    public boolean verifyRequest(String hmac, MultiMap params) {
        StringBuilder query = params.entries().stream()
                .filter(e -> {
                    String k = e.getKey();
                    return !k.equals("hmac") && !k.equals("signature");
                })
                .sorted(Map.Entry.comparingByKey()).collect(StringBuilder::new, (r, e) ->
                                r.append(e.getKey()).append('=').append(e.getValue()).append('&')
                        , StringBuilder::append);
        if (query.length() > 0) {
            query.deleteCharAt(query.length() - 1);
        }
        String digest = hashFunction.hashUnencodedChars(query).toString();
        return digest.equals(hmac);
    }

    @Override
    public boolean verifyData(String hmac, String data) {
        String digest = Base64.getEncoder().encodeToString(hashFunction.hashString(data, StandardCharsets.UTF_8).asBytes());
        return digest.equals(hmac);
    }

    @Override
    public boolean verifyData(String hmac, Buffer data) {
        String digest = Base64.getEncoder().encodeToString(hashFunction.hashBytes(data.getBytes()).asBytes());
        return digest.equals(hmac);
    }

    @Override
    public String getInstallUrl(String shop, String scopes, String redirectUri) {
        try {
            return "https://" + shop + "/admin/oauth/authorize?client_id=" + key + "&scope="
                    + URLEncoder.encode(scopes, StandardCharsets.UTF_8.displayName()) +
                    "&redirect_uri="
                    + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8.displayName());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Session newSession(String shop, String token) {
        return new SessionImpl(shop, token, this);
    }

    public String getKey() {
        return key;
    }

    public String getSecret() {
        return secret;
    }

    public WebClient getWebClient() {
        return webClient;
    }
}
