package com.github.longdt.shopify.client;

import com.github.longdt.shopify.exception.ShopifyException;
import com.github.longdt.shopify.model.*;
import com.github.longdt.shopify.model.internal.*;
import com.github.longdt.shopify.util.HttpStatusVerifier;
import com.github.longdt.shopify.util.Json;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

import java.util.List;

/**
 * Created by thienlong on 23/06/2016.
 */
public class SessionImpl implements Session {
    private final String shop;
    private final String token;
    private final ShopifyClient shopifyClient;
    private final WebClient webClient;

    SessionImpl(String shop, String token, ShopifyClient shopifyClient) {
        this.shop = shop;
        this.token = token;
        this.shopifyClient = shopifyClient;
        webClient = shopifyClient.getWebClient();
    }

    @Override
    public Future<Void> uninstall() {
        return prepareRequest(HttpMethod.DELETE, "/admin/api_permissions/current.json")
                .send()
                .map(HttpStatusVerifier.OK);
    }

    @Override
    public Future<PriceRule> createPriceRule(PriceRule priceRule) {
        Buffer body = Json.encodeToBuffer(new PriceRuleWrapper(priceRule));
        return prepareRequest(HttpMethod.POST, "/admin/price_rules.json")
                .sendBuffer(body)
                .map(resp -> toObject(resp, 201, PriceRuleWrapper.class).getPriceRule());
    }

    @Override
    public Future<List<PriceRule>> findPriceRules(JsonObject query) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/price_rules.json");
        if (query != null) {
            addQueryParam(request, query);
        }
        return request.send()
                .map(resp -> toObject(resp, 200, PriceRuleList.class).getPriceRules());
    }

    @Override
    public Future<PriceRule> updatePriceRule(PriceRule priceRule) {
        Buffer body = Json.encodeToBuffer(new PriceRuleWrapper(priceRule));
        return prepareRequest(HttpMethod.PUT, "/admin/price_rules/" + priceRule.getId() + ".json")
                .sendBuffer(body)
                .map(resp -> toObject(resp, 200, PriceRuleWrapper.class).getPriceRule());
    }

    @Override
    public Future<PriceRule> findPriceRule(Long priceRuleId) {
        return prepareRequest(HttpMethod.PUT, "/admin/price_rules/" + priceRuleId + ".json")
                .send()
                .map(resp -> toObject(resp, 200, PriceRuleWrapper.class).getPriceRule());
    }

    @Override
    public Future<Void> deletePriceRule(Long priceRuleId) {
        return prepareRequest(HttpMethod.DELETE, "/admin/price_rules/" + priceRuleId + ".json")
                .send()
                .map(HttpStatusVerifier.NO_CONTENT);
    }

    @Override
    public Future<DiscountCode> createDiscountCode(Long priceRuleId, String code, int usageCount) {
        Buffer body = Buffer.buffer("{\"discount_code\":{\"code\":");
        Json.encode(code, body);
        body.appendString(",\"usage_count\":").appendString(String.valueOf(usageCount)).appendString("}}");
        return prepareRequest(HttpMethod.POST, "/admin/price_rules/" + priceRuleId + "/discount_codes.json")
                .sendBuffer(body)
                .map(resp -> toObject(resp, 201, DiscountCodeWrapper.class).getDiscountCode());
    }

    @Override
    public Future<DiscountCode> updateDiscountCode(DiscountCode discountCode) {
        Buffer body = Json.encodeToBuffer(new DiscountCodeWrapper(discountCode));
        return prepareRequest(HttpMethod.PUT, "/admin/price_rules/" + discountCode.getPriceRuleId()
                + "/discount_codes/" + discountCode.getId() + ".json")
                .sendBuffer(body)
                .map(resp -> toObject(resp, 200, DiscountCodeWrapper.class).getDiscountCode());
    }

    @Override
    public Future<List<DiscountCode>> findDiscountCodes(Long priceRuleId) {
        return prepareRequest(HttpMethod.GET, "/admin/price_rules/" + priceRuleId + "/discount_codes.json")
                .send()
                .map(resp -> toObject(resp, 200, DiscountCodeList.class).getDiscountCodes());
    }

    @Override
    public Future<DiscountCode> findDiscountCode(Long priceRuleId, Long discountCodeId) {
        return prepareRequest(HttpMethod.GET, "/admin/price_rules/" + priceRuleId
                + "/discount_codes/" + discountCodeId + ".json")
                .send()
                .map(resp -> toObject(resp, 200, DiscountCodeWrapper.class).getDiscountCode());
    }

    @Override
    public Future<DiscountCode> searchDiscountCode(String code, boolean detail) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/discount_codes/lookup.json")
                .addQueryParam("code", code);
        if (detail) {
            return request.followRedirects(true)
                    .send()
                    .map(resp -> toObject(resp, 200, DiscountCodeWrapper.class).getDiscountCode());
        } else {
            return request.followRedirects(false)
                    .send()
                    .map(resp -> {
                        if (resp.statusCode() == 303) {
                            var discountCode = parseDiscountCodeUrl(resp.getHeader("location"));
                            discountCode.setCode(code);
                            return discountCode;
                        } else {
                            throw new ShopifyException(resp.statusCode() + " " + resp.bodyAsString());
                        }
                    });
        }
    }

    private DiscountCode parseDiscountCodeUrl(String url) {
        String prPrefix = "myshopify.com/admin/price_rules/";
        int index = url.indexOf(prPrefix) + prPrefix.length();
        String dcPrefix = "/discount_codes/";
        int end = url.indexOf(dcPrefix);
        DiscountCode discountCode = new DiscountCode();
        discountCode.setPriceRuleId(Long.valueOf(url.substring(index, end)));
        discountCode.setId(Long.valueOf(url.substring(end + dcPrefix.length())));
        return discountCode;
    }

    @Override
    public Future<Void> deleteDiscountCode(Long priceRuleId, Long discountCodeId) {
        return prepareRequest(HttpMethod.DELETE, "/admin/price_rules/" + priceRuleId
                + "/discount_codes/" + discountCodeId + ".json")
                .send()
                .map(HttpStatusVerifier.NO_CONTENT);
    }

    @Override
    public Future<DiscountCodeCreation> createDiscountCodes(Long priceRuleId, List<String> codes) {
        if (codes.isEmpty() || codes.size() > 100) {
            return Future.failedFuture(
                    new ShopifyException("Creates a discount code creation job: codes size must in range [1, 100]"));
        }
        Buffer body = Buffer.buffer("{\"discount_codes\":[{\"code\":");
        Json.encode(codes.get(0), body);
        body.appendString("}");
        for (int i = 1; i < codes.size(); ++i) {
            body.appendString(",{\"code\":");
            Json.encode(codes.get(i), body);
            body.appendString("}");
        }
        body.appendString("]}");
        return prepareRequest(HttpMethod.POST, "/admin/price_rules/" + priceRuleId + "/batch.json")
                .sendBuffer(body)
                .map(resp -> toObject(resp, 201, DiscountCodeCreationWrapper.class).getDiscountCodeCreation());
    }

    @Override
    public Future<DiscountCodeCreation> findDiscountCodeCreation(Long priceRuleId, Long batchId) {
        return prepareRequest(HttpMethod.GET, "/admin/price_rules/" + priceRuleId
                + "/batch/" + batchId + ".json")
                .send()
                .map(resp -> toObject(resp, 200, DiscountCodeCreationWrapper.class).getDiscountCodeCreation());
    }

    @Override
    public Future<List<DiscountCodeResult>> findDiscountCodeResults(Long priceRuleId, Long batchId) {
        return prepareRequest(HttpMethod.GET, "/admin/price_rules/" + priceRuleId + "/batch/" + batchId + "/discount_codes.json")
                .send()
                .map(resp -> toObject(resp, 200, DiscountCodeResultList.class).getDiscountCodes());
    }

    @Override
    public Future<ProductVariant> findVariant(Long variantId) {
        return prepareRequest(HttpMethod.GET, "/admin/variants/" + variantId + ".json")
                .send()
                .map(resp -> toObject(resp, 200, ProductVariant.class));
    }

    @Override
    public Future<Webhook> createWebhook(String topic, String address) {
        JsonObject webhook = new JsonObject().put("topic", topic)
                .put("address", address);
        return prepareRequest(HttpMethod.POST, "/admin/webhooks.json")
                .sendJsonObject(new JsonObject().put("webhook", webhook))
                .map(resp -> toObject(resp, 201, WebhookWrapper.class).getWebhook());
    }

    @Override
    public Future<List<Webhook>> findWebhooks(JsonObject query) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/webhooks.json");
        if (query != null) {
            addQueryParam(request, query);
        }
        return request.send()
                .map(resp -> toObject(resp, 200, WebhookList.class).getWebhooks());
    }

    @Override
    public Future<Long> countWebhooks(String address, String topic) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/webhooks/count.json");
        if (address != null) {
            request.addQueryParam("address", address);
        }
        if (topic != null) {
            request.addQueryParam("topic", topic);
        }
        return sendCount(request);
    }

    @Override
    public Future<Webhook> findWebhook(Long webhookId, String fields) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/webhooks/" + webhookId + ".json");
        if (fields != null) {
            request.addQueryParam("fields", fields);
        }
        return request.send()
                .map(resp -> toObject(resp, 200, WebhookWrapper.class).getWebhook());
    }

    @Override
    public Future<Webhook> updateWebhook(Webhook webhook) {
        JsonObject whJson = new JsonObject().put("id", webhook.getId());
        if (webhook.getTopic() != null) {
            whJson.put("topic", webhook.getTopic());
        }
        if (webhook.getAddress() != null) {
            whJson.put("address", webhook.getAddress());
        }
        return prepareRequest(HttpMethod.PUT, "/admin/webhooks/" + webhook.getId() + ".json")
                .sendJsonObject(new JsonObject().put("webhook", whJson))
                .map(resp -> toObject(resp, 200, WebhookWrapper.class).getWebhook());
    }

    @Override
    public Future<Void> deleteWebhook(Long webhookId) {
        return prepareRequest(HttpMethod.DELETE, "/admin/webhooks/" + webhookId + ".json")
                .send()
                .map(HttpStatusVerifier.OK);
    }

    @Override
    public Future<List<ScriptTag>> findScriptTags(JsonObject query) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/script_tags.json");
        if (query != null) {
            addQueryParam(request, query);
        }
        return request.send()
                .map(resp -> toObject(resp, 200, ScriptTagList.class).getScriptTags());
    }

    @Override
    public Future<Long> countScriptTags(String src) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/script_tags/count.json");
        if (src != null) {
            request.addQueryParam("src", src);
        }
        return sendCount(request);
    }

    @Override
    public Future<ScriptTag> findScriptTag(Long scriptTagId, String fields) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/script_tags/" + scriptTagId + ".json");
        if (fields != null) {
            request.addQueryParam("fields", fields);
        }
        return request.send()
                .map(resp -> toObject(resp, 200, ScriptTagWrapper.class).getScriptTag());
    }

    @Override
    public Future<ScriptTag> createScriptTag(String event, String src) {
        JsonObject scriptTag = new JsonObject().put("event", event).put("src", src);
        return prepareRequest(HttpMethod.POST, "/admin/script_tags.json")
                .sendJsonObject(new JsonObject().put("script_tag", scriptTag))
                .map(resp -> toObject(resp, 201, ScriptTagWrapper.class).getScriptTag());
    }

    @Override
    public Future<ScriptTag> updateScriptTag(ScriptTag scriptTag) {
        JsonObject scriptJson = new JsonObject().put("id", scriptTag.getId()).put("src", scriptTag.getSrc());
        return prepareRequest(HttpMethod.PUT, "/admin/script_tags/" + scriptTag.getId() + ".json")
                .sendJsonObject(new JsonObject().put("script_tag", scriptJson))
                .map(resp -> toObject(resp, 200, ScriptTagWrapper.class).getScriptTag());
    }

    @Override
    public Future<Void> deleteScriptTag(Long scriptTagId) {
        return prepareRequest(HttpMethod.DELETE, "/admin/script_tags/" + scriptTagId + ".json")
                .send()
                .map(HttpStatusVerifier.OK);
    }

    @Override
    public Future<List<Asset>> findAssets(Long themeId, String fields) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/themes/" + themeId + "/assets.json");
        if (fields != null) {
            request.addQueryParam("fields", fields);
        }
        return request.send()
                .map(resp -> toObject(resp, 200, AssetList.class).getAssets());
    }

    @Override
    public Future<Asset> findAsset(Long themeId, String assetKey, String fields) {
        return prepareRequest(HttpMethod.GET, "/admin/themes/" + themeId + "/assets.json")
                .addQueryParam("asset[key]", assetKey)
                .send()
                .map(resp -> toObject(resp, 200, AssetWrapper.class).getAsset());
    }

    @Override
    public Future<Asset> updateAsset(Asset asset) {
        Buffer body = Json.encodeToBuffer(new AssetWrapper(asset));
        return updateAsset0(asset.getThemeId(), body);
    }

    @Override
    public Future<Asset> uploadAsset(Asset asset, String src) {
        Buffer body = Buffer.buffer("{\"asset\":{\"key\":");
        Json.encode(asset.getKey(), body);
        body.appendString(",\"src\":");
        Json.encode(src, body);
        body.appendString("}}");
        return updateAsset0(asset.getThemeId(), body);
    }

    @Override
    public Future<Asset> copyAsset(Asset asset, String sourceKey) {
        Buffer body = Buffer.buffer("{\"asset\":{\"key\":");
        Json.encode(asset.getKey(), body);
        body.appendString(",\"source_key\":");
        Json.encode(sourceKey, body);
        body.appendString("}}");
        return updateAsset0(asset.getThemeId(), body);
    }

    private Future<Asset> updateAsset0(Long themeId, Buffer body) {
        return prepareRequest(HttpMethod.PUT, "/admin/themes/" + themeId + "/assets.json")
                .sendBuffer(body)
                .map(resp -> toObject(resp, 200, AssetWrapper.class).getAsset());
    }

    @Override
    public Future<Void> deleteAsset(Long themeId, String assetKey) {
        return prepareRequest(HttpMethod.DELETE, "/admin/themes/" + themeId + "/assets.json")
                .addQueryParam("asset[key]", assetKey)
                .send()
                .map(HttpStatusVerifier.OK);
    }

    @Override
    public Future<List<Theme>> findThemes(String fields) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/themes.json");
        if (fields != null) {
            request.addQueryParam("fields", fields);
        }
        return request.send()
                .map(resp -> toObject(resp, 200, ThemeList.class).getThemes());
    }

    @Override
    public Future<Theme> findTheme(Long themeId, String fields) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/themes/" + themeId + ".json");
        if (fields != null) {
            request.addQueryParam("fields", fields);
        }
        return request.send()
                .map(resp -> toObject(resp, 200, ThemeWrapper.class).getTheme());
    }

    @Override
    public Future<Theme> createTheme(String name, String src, Theme.Role role) {
        JsonObject theme = new JsonObject().put("name", name).put("src", src);
        if (role != null) {
            theme.put("role", role);
        }

        return prepareRequest(HttpMethod.POST, "/admin/themes.json")
                .sendJsonObject(new JsonObject().put("theme", theme))
                .map(resp -> toObject(resp, 201, ThemeWrapper.class).getTheme());
    }

    @Override
    public Future<Theme> updateTheme(Theme theme) {
        JsonObject thJson = new JsonObject().put("id", theme.getId());
        if (theme.getName() != null) {
            thJson.put("name", theme.getName());
        }
        if (theme.getRole() != null) {
            thJson.put("role", theme.getRole());
        }

        return prepareRequest(HttpMethod.PUT, "/admin/themes/" + theme.getId() + ".json")
                .sendJsonObject(new JsonObject().put("theme", thJson))
                .map(resp -> toObject(resp, 200, ThemeWrapper.class).getTheme());
    }

    @Override
    public Future<Void> deleteTheme(Long themeId) {
        return prepareRequest(HttpMethod.DELETE, "/admin/themes/" + themeId + ".json")
                .send()
                .map(HttpStatusVerifier.OK);
    }

    @Override
    public Future<List<Order>> findOrders(JsonObject query) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/orders.json");
        if (query != null) {
            addQueryParam(request, query);
        }
        return request.send()
                .map(resp -> toObject(resp, 200, OrderList.class).getOrders());
    }

    @Override
    public Future<Long> countOrders(JsonObject query) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/orders/count.json");
        if (query != null) {
            addQueryParam(request, query);
        }
        return sendCount(request);
    }

    @Override
    public Future<Order> findOrder(Long orderId, String fields) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/orders/" + orderId + ".json");
        if (fields != null) {
            request.addQueryParam("fields", fields);
        }
        return request.send()
                .map(resp -> toObject(resp, 200, OrderWrapper.class).getOrder());
    }

    @Override
    public Future<Order> closeOrder(Long orderId) {
        return prepareRequest(HttpMethod.POST, "/admin/orders/" + orderId + "/close.json")
                .send()
                .map(resp -> toObject(resp, 200, OrderWrapper.class).getOrder());
    }

    @Override
    public Future<Order> openOrder(Long orderId) {
        return prepareRequest(HttpMethod.POST, "/admin/orders/" + orderId + "/open.json")
                .send()
                .map(resp -> toObject(resp, 200, OrderWrapper.class).getOrder());
    }

    @Override
    public Future<Order> cancelOrder(Long orderId, JsonObject options) {
        return prepareRequest(HttpMethod.POST, "/admin/orders/" + orderId + "/cancel.json")
                .sendJsonObject(options)
                .map(resp -> toObject(resp, 200, OrderWrapper.class).getOrder());
    }

    @Override
    public Future<Order> createOrder(Order order, JsonObject options) {
        Buffer body = Json.encodeToBuffer(new OrderWrapper(order));
        if (options != null && !options.getMap().isEmpty()) {
            int replacePos = body.length() - 2;
            Json.encode(options.getMap(), body);
            body.setString(replacePos, ",  ");
            body.appendByte((byte) '}');
        }
        return prepareRequest(HttpMethod.POST, "/admin/orders.json")
                .sendBuffer(body)
                .map(resp -> toObject(resp, 200, OrderWrapper.class).getOrder());
    }

    @Override
    public Future<Order> updateOrder(Order order) {
        Buffer body = Json.encodeToBuffer(new OrderWrapper(order));
        return prepareRequest(HttpMethod.PUT, "/admin/orders/" + order.getId() + ".json")
                .sendBuffer(body)
                .map(resp -> toObject(resp, 200, OrderWrapper.class).getOrder());
    }

    @Override
    public Future<Void> deleteOrder(Long orderId) {
        return prepareRequest(HttpMethod.DELETE, "/admin/orders/" + orderId + ".json")
                .send()
                .map(HttpStatusVerifier.OK);
    }

    @Override
    public Future<Shop> findShop(String fields) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/shop.json");
        if (fields != null) {
            request.addQueryParam("fields", fields);
        }
        return request.send().map(resp -> toObject(resp, 200, ShopWrapper.class).getShop());
    }

    private HttpRequest<Buffer> prepareRequest(HttpMethod httpMethod, String requestUri) {
        return webClient.request(httpMethod, 443, shop, requestUri)
                .ssl(true)
                .putHeader("X-Shopify-Access-Token", token)
                .putHeader("Content-Type", "application/json");
    }

    private HttpRequest<Buffer> addQueryParam(HttpRequest<Buffer> request, JsonObject query) {
        query.forEach(kv -> request.addQueryParam(kv.getKey(), kv.getValue().toString()));
        return request;
    }

    private Future<Long> sendCount(HttpRequest<Buffer> request) {
        return request.send().map(resp -> toObject(resp, 200, Count.class).getCount());
    }

    private static <T> T toObject(HttpResponse<Buffer> response, int expectedStatus, Class<T> clazz) {
        if (response.statusCode() == expectedStatus) {
            return Json.decodeValue(response.body(), clazz);
        } else {
            throw new ShopifyException(response.statusCode() + " " + response.bodyAsString());
        }
    }
}
