package com.github.longdt.shopify.client;

import com.github.longdt.shopify.exception.ShopifyException;
import com.github.longdt.shopify.model.*;
import com.github.longdt.shopify.model.internal.*;
import com.github.longdt.shopify.util.HttpResponseTransformer;
import com.github.longdt.shopify.util.Json;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
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
                .send().map(new HttpResponseTransformer<>(200, Void.class));
    }

    @Override
    public Future<PriceRule> createPriceRule(PriceRule priceRule) {
        Buffer body = Json.encodeToBuffer(new PriceRuleWrapper(priceRule));
        return prepareRequest(HttpMethod.POST, "/admin/price_rules.json")
                .sendBuffer(body)
                .map(new HttpResponseTransformer<>(201, PriceRuleWrapper.class))
                .map(PriceRuleWrapper::getPriceRule);
    }

    @Override
    public Future<List<PriceRule>> findPriceRules(JsonObject query) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/price_rules.json");
        if (query != null) {
            addQueryParam(request, query);
        }
        return request.send()
                .map(new HttpResponseTransformer<>(200, PriceRuleList.class))
                .map(PriceRuleList::getPriceRules);
    }

    @Override
    public Future<PriceRule> updatePriceRule(PriceRule priceRule) {
        Buffer body = Json.encodeToBuffer(new PriceRuleWrapper(priceRule));
        Future<PriceRuleWrapper> future = Future.future();
        prepareRequest(HttpMethod.PUT, "/admin/price_rules/" + priceRule.getId() + ".json")
                .sendBuffer(body,
                        new HttpResponseTransformer<>(200, PriceRuleWrapper.class, future));
        future.map(PriceRuleWrapper::getPriceRule).setHandler(resultHandler);
    }

    @Override
    public void findPriceRule(Long priceRuleId, Handler<AsyncResult<PriceRule>> resultHandler) {
        Future<PriceRuleWrapper> future = Future.future();
        prepareRequest(HttpMethod.PUT, "/admin/price_rules/" + priceRuleId + ".json")
                .send(new HttpResponseTransformer<>(200, PriceRuleWrapper.class, future));
        future.map(PriceRuleWrapper::getPriceRule).setHandler(resultHandler);
    }

    @Override
    public void deletePriceRule(Long priceRuleId, Handler<AsyncResult<Void>> resultHandler) {
        prepareRequest(HttpMethod.DELETE, "/admin/price_rules/" + priceRuleId + ".json")
                .send(new HttpResponseTransformer<>(204, Void.class, resultHandler));
    }

    @Override
    public void createDiscountCode(Long priceRuleId, String code, int usageCount, Handler<AsyncResult<DiscountCode>> resultHandler) {
        Buffer body = Buffer.buffer("{\"discount_code\":{\"code\":");
        Json.encode(code, body);
        body.appendString(",\"usage_count\":").appendString(String.valueOf(usageCount)).appendString("}}");
        Future<DiscountCodeWrapper> future = Future.future();
        prepareRequest(HttpMethod.POST, "/admin/price_rules/" + priceRuleId + "/discount_codes.json")
                .sendBuffer(body,
                        new HttpResponseTransformer<>(201, DiscountCodeWrapper.class, future));
        future.map(DiscountCodeWrapper::getDiscountCode).setHandler(resultHandler);

    }

    @Override
    public void updateDiscountCode(DiscountCode discountCode, Handler<AsyncResult<DiscountCode>> resultHandler) {
        Buffer body = Json.encodeToBuffer(new DiscountCodeWrapper(discountCode));
        Future<DiscountCodeWrapper> future = Future.future();
        prepareRequest(HttpMethod.PUT, "/admin/price_rules/" + discountCode.getPriceRuleId()
                + "/discount_codes/" + discountCode.getId() + ".json")
                .sendBuffer(body,
                        new HttpResponseTransformer<>(200, DiscountCodeWrapper.class, future));
        future.map(DiscountCodeWrapper::getDiscountCode).setHandler(resultHandler);
    }

    @Override
    public void findDiscountCodes(Long priceRuleId, Handler<AsyncResult<List<DiscountCode>>> resultHandler) {
        Future<DiscountCodeList> future = Future.future();
        prepareRequest(HttpMethod.GET, "/admin/price_rules/" + priceRuleId + "/discount_codes.json")
                .send(new HttpResponseTransformer<>(200, DiscountCodeList.class, future));
        future.map(DiscountCodeList::getDiscountCodes).setHandler(resultHandler);
    }

    @Override
    public void findDiscountCode(Long priceRuleId, Long discountCodeId, Handler<AsyncResult<DiscountCode>> resultHandler) {
        Future<DiscountCodeWrapper> future = Future.future();
        prepareRequest(HttpMethod.GET, "/admin/price_rules/" + priceRuleId
                + "/discount_codes/" + discountCodeId + ".json")
                .send(new HttpResponseTransformer<>(200, DiscountCodeWrapper.class, future));
        future.map(DiscountCodeWrapper::getDiscountCode).setHandler(resultHandler);
    }

    @Override
    public void searchDiscountCode(String code, boolean detail, Handler<AsyncResult<DiscountCode>> resultHandler) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/discount_codes/lookup.json")
                .addQueryParam("code", code);
        if (detail) {
            Future<DiscountCodeWrapper> future = Future.future();
            request.followRedirects(true)
                    .send(new HttpResponseTransformer<>(200, DiscountCodeWrapper.class, future));
            future.map(DiscountCodeWrapper::getDiscountCode).setHandler(resultHandler);
        } else {
            request.followRedirects(false)
                    .send(ar -> {
                        if (ar.succeeded()) {
                            HttpResponse<Buffer> response = ar.result();
                            if (response.statusCode() == 303) {
                                DiscountCode discountCode = parseDiscountCodeUrl(response.getHeader("location"));
                                discountCode.setCode(code);
                                resultHandler.handle(Future.succeededFuture(discountCode));
                            } else {
                                resultHandler.handle(Future.failedFuture(new ShopifyException(response.statusCode() + " " + response.bodyAsString())));
                            }
                        } else {
                            resultHandler.handle(Future.failedFuture(ar.cause()));
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
    public void deleteDiscountCode(Long priceRuleId, Long discountCodeId, Handler<AsyncResult<Void>> resultHandler) {
        prepareRequest(HttpMethod.DELETE, "/admin/price_rules/" + priceRuleId
                + "/discount_codes/" + discountCodeId + ".json")
                .send(new HttpResponseTransformer<>(204, Void.class, resultHandler));
    }

    @Override
    public void createDiscountCodes(Long priceRuleId, List<String> codes, Handler<AsyncResult<DiscountCodeCreation>> resultHandler) {
        if (codes.isEmpty() || codes.size() > 100) {
            resultHandler.handle(Future.failedFuture(
                    new ShopifyException("Creates a discount code creation job: codes size must in range [1, 100]")));
            return;
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
        Future<DiscountCodeCreationWrapper> future = Future.future();
        prepareRequest(HttpMethod.POST, "/admin/price_rules/" + priceRuleId + "/batch.json")
                .sendBuffer(body,
                        new HttpResponseTransformer<>(201, DiscountCodeCreationWrapper.class, future));
        future.map(DiscountCodeCreationWrapper::getDiscountCodeCreation).setHandler(resultHandler);
    }

    @Override
    public void findDiscountCodeCreation(Long priceRuleId, Long batchId, Handler<AsyncResult<DiscountCodeCreation>> resultHandler) {
        Future<DiscountCodeCreationWrapper> future = Future.future();
        prepareRequest(HttpMethod.GET, "/admin/price_rules/" + priceRuleId
                + "/batch/" + batchId + ".json")
                .send(new HttpResponseTransformer<>(200, DiscountCodeCreationWrapper.class, future));
        future.map(DiscountCodeCreationWrapper::getDiscountCodeCreation).setHandler(resultHandler);
    }

    @Override
    public void findDiscountCodeResults(Long priceRuleId, Long batchId, Handler<AsyncResult<List<DiscountCodeResult>>> resultHandler) {
        Future<DiscountCodeResultList> future = Future.future();
        prepareRequest(HttpMethod.GET, "/admin/price_rules/" + priceRuleId + "/batch/" + batchId + "/discount_codes.json")
                .send(new HttpResponseTransformer<>(200, DiscountCodeResultList.class, future));
        future.map(DiscountCodeResultList::getDiscountCodes).setHandler(resultHandler);
    }

    @Override
    public void findVariant(Long variantId, Handler<AsyncResult<ProductVariant>> resultHandler) {
        prepareRequest(HttpMethod.GET, "/admin/variants/" + variantId + ".json")
                .send(new HttpResponseTransformer<>(200, ProductVariant.class, resultHandler));
    }

    @Override
    public void createWebhook(String topic, String address, Handler<AsyncResult<Webhook>> resultHandler) {
        JsonObject webhook = new JsonObject().put("topic", topic)
                .put("address", address);
        Future<WebhookWrapper> future = Future.future();
        prepareRequest(HttpMethod.POST, "/admin/webhooks.json")
                .sendJsonObject(new JsonObject().put("webhook", webhook),
                        new HttpResponseTransformer<>(201, WebhookWrapper.class, future));
        future.map(WebhookWrapper::getWebhook).setHandler(resultHandler);
    }

    @Override
    public void findWebhooks(JsonObject query, Handler<AsyncResult<List<Webhook>>> resultHandler) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/webhooks.json");
        if (query != null) {
            addQueryParam(request, query);
        }
        Future<WebhookList> future = Future.future();
        request.send(new HttpResponseTransformer<>(200, WebhookList.class, future));
        future.map(WebhookList::getWebhooks).setHandler(resultHandler);
    }

    @Override
    public void countWebhooks(String address, String topic, Handler<AsyncResult<Long>> resultHandler) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/webhooks/count.json");
        if (address != null) {
            request.addQueryParam("address", address);
        }
        if (topic != null) {
            request.addQueryParam("topic", topic);
        }
        sendCount(request, resultHandler);
    }

    @Override
    public void findWebhook(Long webhookId, String fields, Handler<AsyncResult<Webhook>> resultHandler) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/webhooks/" + webhookId + ".json");
        if (fields != null) {
            request.addQueryParam("fields", fields);
        }
        Future<WebhookWrapper> future = Future.future();
        request.send(new HttpResponseTransformer<>(200, WebhookWrapper.class, future));
        future.map(WebhookWrapper::getWebhook).setHandler(resultHandler);
    }

    @Override
    public void updateWebhook(Webhook webhook, Handler<AsyncResult<Webhook>> resultHandler) {
        JsonObject whJson = new JsonObject().put("id", webhook.getId());
        if (webhook.getTopic() != null) {
            whJson.put("topic", webhook.getTopic());
        }
        if (webhook.getAddress() != null) {
            whJson.put("address", webhook.getAddress());
        }
        Future<WebhookWrapper> future = Future.future();
        prepareRequest(HttpMethod.PUT, "/admin/webhooks/" + webhook.getId() + ".json")
                .sendJsonObject(new JsonObject().put("webhook", whJson),
                        new HttpResponseTransformer<>(200, WebhookWrapper.class, future));
        future.map(WebhookWrapper::getWebhook).setHandler(resultHandler);
    }

    @Override
    public void deleteWebhook(Long webhookId, Handler<AsyncResult<Void>> resultHandler) {
        prepareRequest(HttpMethod.DELETE, "/admin/webhooks/" + webhookId + ".json")
                .send(new HttpResponseTransformer<>(200, Void.class, resultHandler));
    }

    @Override
    public void findScriptTags(JsonObject query, Handler<AsyncResult<List<ScriptTag>>> resultHandler) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/script_tags.json");
        if (query != null) {
            addQueryParam(request, query);
        }
        Future<ScriptTagList> future = Future.future();
        request.send(new HttpResponseTransformer<>(200, ScriptTagList.class, future));
        future.map(ScriptTagList::getScriptTags).setHandler(resultHandler);
    }

    @Override
    public void countScriptTags(String src, Handler<AsyncResult<Long>> resultHandler) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/script_tags/count.json");
        if (src != null) {
            request.addQueryParam("src", src);
        }
        sendCount(request, resultHandler);
    }

    @Override
    public void findScriptTag(Long scriptTagId, String fields, Handler<AsyncResult<ScriptTag>> resultHandler) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/script_tags/" + scriptTagId + ".json");
        if (fields != null) {
            request.addQueryParam("fields", fields);
        }
        Future<ScriptTagWrapper> future = Future.future();
        request.send(new HttpResponseTransformer<>(200, ScriptTagWrapper.class, future));
        future.map(ScriptTagWrapper::getScriptTag).setHandler(resultHandler);
    }

    @Override
    public void createScriptTag(String event, String src, Handler<AsyncResult<ScriptTag>> resultHandler) {
        JsonObject scriptTag = new JsonObject().put("event", event).put("src", src);
        Future<ScriptTagWrapper> future = Future.future();
        prepareRequest(HttpMethod.POST, "/admin/script_tags.json")
                .sendJsonObject(new JsonObject().put("script_tag", scriptTag),
                        new HttpResponseTransformer<>(201, ScriptTagWrapper.class, future));
        future.map(ScriptTagWrapper::getScriptTag).setHandler(resultHandler);
    }

    @Override
    public void updateScriptTag(ScriptTag scriptTag, Handler<AsyncResult<ScriptTag>> resultHandler) {
        JsonObject scriptJson = new JsonObject().put("id", scriptTag.getId()).put("src", scriptTag.getSrc());
        Future<ScriptTagWrapper> future = Future.future();
        prepareRequest(HttpMethod.PUT, "/admin/script_tags/" + scriptTag.getId() + ".json")
                .sendJsonObject(new JsonObject().put("script_tag", scriptJson),
                        new HttpResponseTransformer<>(200, ScriptTagWrapper.class, future));
        future.map(ScriptTagWrapper::getScriptTag).setHandler(resultHandler);
    }

    @Override
    public void deleteScriptTag(Long scriptTagId, Handler<AsyncResult<Void>> resultHandler) {
        prepareRequest(HttpMethod.DELETE, "/admin/script_tags/" + scriptTagId + ".json")
                .send(new HttpResponseTransformer<>(200, Void.class, resultHandler));
    }

    @Override
    public void findAssets(Long themeId, String fields, Handler<AsyncResult<List<Asset>>> resultHandler) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/themes/" + themeId + "/assets.json");
        if (fields != null) {
            request.addQueryParam("fields", fields);
        }
        Future<AssetList> future = Future.future();
        request.send(new HttpResponseTransformer<>(200, AssetList.class, future));
        future.map(AssetList::getAssets).setHandler(resultHandler);
    }

    @Override
    public void findAsset(Long themeId, String assetKey, String fields, Handler<AsyncResult<Asset>> resultHandler) {
        Future<AssetWrapper> future = Future.future();
        prepareRequest(HttpMethod.GET, "/admin/themes/" + themeId + "/assets.json")
                .addQueryParam("asset[key]", assetKey)
                .send(new HttpResponseTransformer<>(200, AssetWrapper.class, future));
        future.map(AssetWrapper::getAsset).setHandler(resultHandler);
    }

    @Override
    public void updateAsset(Asset asset, Handler<AsyncResult<Asset>> resultHandler) {
        Buffer body = Json.encodeToBuffer(new AssetWrapper(asset));
        updateAsset0(asset.getThemeId(), body, resultHandler);
    }

    @Override
    public void uploadAsset(Asset asset, String src, Handler<AsyncResult<Asset>> resultHandler) {
        Buffer body = Buffer.buffer("{\"asset\":{\"key\":");
        Json.encode(asset.getKey(), body);
        body.appendString(",\"src\":");
        Json.encode(src, body);
        body.appendString("}}");
        updateAsset0(asset.getThemeId(), body, resultHandler);
    }

    @Override
    public void copyAsset(Asset asset, String sourceKey, Handler<AsyncResult<Asset>> resultHandler) {
        Buffer body = Buffer.buffer("{\"asset\":{\"key\":");
        Json.encode(asset.getKey(), body);
        body.appendString(",\"source_key\":");
        Json.encode(sourceKey, body);
        body.appendString("}}");
        updateAsset0(asset.getThemeId(), body, resultHandler);
    }

    private void updateAsset0(Long themeId, Buffer body, Handler<AsyncResult<Asset>> resultHandler) {
        Future<AssetWrapper> future = Future.future();
        prepareRequest(HttpMethod.PUT, "/admin/themes/" + themeId + "/assets.json")
                .sendBuffer(body,
                        new HttpResponseTransformer<>(200, AssetWrapper.class, future));
        future.map(AssetWrapper::getAsset).setHandler(resultHandler);
    }

    @Override
    public void deleteAsset(Long themeId, String assetKey, Handler<AsyncResult<Void>> resultHandler) {
        prepareRequest(HttpMethod.DELETE, "/admin/themes/" + themeId + "/assets.json")
                .addQueryParam("asset[key]", assetKey)
                .send(new HttpResponseTransformer<>(200, Void.class, resultHandler));
    }

    @Override
    public void findThemes(String fields, Handler<AsyncResult<List<Theme>>> resultHandler) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/themes.json");
        if (fields != null) {
            request.addQueryParam("fields", fields);
        }
        Future<ThemeList> future = Future.future();
        request.send(new HttpResponseTransformer<>(200, ThemeList.class, future));
        future.map(ThemeList::getThemes).setHandler(resultHandler);
    }

    @Override
    public void findTheme(Long themeId, String fields, Handler<AsyncResult<Theme>> resultHandler) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/themes/" + themeId + ".json");
        if (fields != null) {
            request.addQueryParam("fields", fields);
        }
        Future<ThemeWrapper> future = Future.future();
        request.send(new HttpResponseTransformer<>(200, ThemeWrapper.class, future));
        future.map(ThemeWrapper::getTheme).setHandler(resultHandler);
    }

    @Override
    public void createTheme(String name, String src, Theme.Role role, Handler<AsyncResult<Theme>> resultHandler) {
        JsonObject theme = new JsonObject().put("name", name).put("src", src);
        if (role != null) {
            theme.put("role", role);
        }

        Future<ThemeWrapper> future = Future.future();
        prepareRequest(HttpMethod.POST, "/admin/themes.json")
                .sendJsonObject(new JsonObject().put("theme", theme),
                        new HttpResponseTransformer<>(201, ThemeWrapper.class, future));
        future.map(ThemeWrapper::getTheme).setHandler(resultHandler);
    }

    @Override
    public void updateTheme(Theme theme, Handler<AsyncResult<Theme>> resultHandler) {
        JsonObject thJson = new JsonObject().put("id", theme.getId());
        if (theme.getName() != null) {
            thJson.put("name", theme.getName());
        }
        if (theme.getRole() != null) {
            thJson.put("role", theme.getRole());
        }

        Future<ThemeWrapper> future = Future.future();
        prepareRequest(HttpMethod.PUT, "/admin/themes/" + theme.getId() + ".json")
                .sendJsonObject(new JsonObject().put("theme", thJson),
                        new HttpResponseTransformer<>(200, ThemeWrapper.class, future));
        future.map(ThemeWrapper::getTheme).setHandler(resultHandler);
    }

    @Override
    public void deleteTheme(Long themeId, Handler<AsyncResult<Void>> resultHandler) {
        prepareRequest(HttpMethod.DELETE, "/admin/themes/" + themeId + ".json")
                .send(new HttpResponseTransformer<>(200, Void.class, resultHandler));
    }

    @Override
    public void findOrders(JsonObject query, Handler<AsyncResult<List<Order>>> resultHandler) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/orders.json");
        if (query != null) {
            addQueryParam(request, query);
        }
        Future<OrderList> future = Future.future();
        request.send(new HttpResponseTransformer<>(200, OrderList.class, future));
        future.map(OrderList::getOrders).setHandler(resultHandler);
    }

    @Override
    public void countOrders(JsonObject query, Handler<AsyncResult<Long>> resultHandler) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/orders/count.json");
        if (query != null) {
            addQueryParam(request, query);
        }
        sendCount(request, resultHandler);
    }

    @Override
    public void findOrder(Long orderId, String fields, Handler<AsyncResult<Order>> resultHandler) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/orders/" + orderId + ".json");
        if (fields != null) {
            request.addQueryParam("fields", fields);
        }
        Future<OrderWrapper> future = Future.future();
        request.send(new HttpResponseTransformer<>(200, OrderWrapper.class, future));
        future.map(OrderWrapper::getOrder).setHandler(resultHandler);
    }

    @Override
    public void closeOrder(Long orderId, Handler<AsyncResult<Order>> resultHandler) {
        Future<OrderWrapper> future = Future.future();
        prepareRequest(HttpMethod.POST, "/admin/orders/" + orderId + "/close.json")
                .send(new HttpResponseTransformer<>(200, OrderWrapper.class, future));
        future.map(OrderWrapper::getOrder).setHandler(resultHandler);
    }

    @Override
    public void openOrder(Long orderId, Handler<AsyncResult<Order>> resultHandler) {
        Future<OrderWrapper> future = Future.future();
        prepareRequest(HttpMethod.POST, "/admin/orders/" + orderId + "/open.json")
                .send(new HttpResponseTransformer<>(200, OrderWrapper.class, future));
        future.map(OrderWrapper::getOrder).setHandler(resultHandler);
    }

    @Override
    public void cancelOrder(Long orderId, JsonObject options, Handler<AsyncResult<Order>> resultHandler) {
        Future<OrderWrapper> future = Future.future();
        prepareRequest(HttpMethod.POST, "/admin/orders/" + orderId + "/cancel.json")
                .sendJsonObject(options, new HttpResponseTransformer<>(200, OrderWrapper.class, future));
        future.map(OrderWrapper::getOrder).setHandler(resultHandler);
    }

    @Override
    public void createOrder(Order order, JsonObject options, Handler<AsyncResult<Order>> resultHandler) {
        Buffer body = Json.encodeToBuffer(new OrderWrapper(order));
        if (options != null && !options.getMap().isEmpty()) {
            int replacePos = body.length() - 2;
            Json.encode(options.getMap(), body);
            body.setString(replacePos, ",  ");
            body.appendByte((byte) '}');
        }
        Future<OrderWrapper> future = Future.future();
        prepareRequest(HttpMethod.POST, "/admin/orders.json").sendBuffer(body, new HttpResponseTransformer<>(200, OrderWrapper.class, future));
        future.map(OrderWrapper::getOrder).setHandler(resultHandler);
    }

    @Override
    public void updateOrder(Order order, Handler<AsyncResult<Order>> resultHandler) {
        Buffer body = Json.encodeToBuffer(new OrderWrapper(order));
        Future<OrderWrapper> future = Future.future();
        prepareRequest(HttpMethod.PUT, "/admin/orders/" + order.getId() + ".json")
                .sendBuffer(body, new HttpResponseTransformer<>(200, OrderWrapper.class, future));
        future.map(OrderWrapper::getOrder).setHandler(resultHandler);
    }

    @Override
    public Future<Void> deleteOrder(Long orderId) {
        prepareRequest(HttpMethod.DELETE, "/admin/orders/" + orderId + ".json")
                .send()
                .map()new HttpResponseTransformer<>(200, Void.class, resultHandler));
    }

    @Override
    public Future<Shop> findShop(String fields) {
        HttpRequest<Buffer> request = prepareRequest(HttpMethod.GET, "/admin/shop.json");
        if (fields != null) {
            request.addQueryParam("fields", fields);
        }
        return request.send().map(v -> new HttpResponseTransformer<>(200, ShopWrapper.class).apply(v).getShop());
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
        return request.send().map(v -> new HttpResponseTransformer<>(200, Count.class).apply(v).getCount());
    }
}
