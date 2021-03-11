package com.github.longdt.shopify.client;

import com.github.longdt.shopify.model.*;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface Session {
    Future<Void> uninstall();

    /* Start PriceRule Api */
    Future<PriceRule> createPriceRule(PriceRule priceRule);

    Future<PriceRule> updatePriceRule(PriceRule priceRule);

    Future<List<PriceRule>> findPriceRules();

    default Future<List<PriceRule>> findPriceRules(int page, int limit) {
        return findPriceRules(new JsonObject().put("page", page).put("limit", limit));
    }

    /**
     * get list PriceRule by a given query (query can nullable).<br/>
     * supported filter:
     * <ul>
     *     <li>limit</li>
     *     <li>page</li>
     *     <li>since_id</li>
     *     <li>created_at_max</li>
     *     <li>created_at_min</li>
     *     <li>updated_at_min</li>
     *     <li>updated_at_max</li>
     *     <li>starts_at_min</li>
     *     <li>starts_at_max</li>
     *     <li>ends_at_min</li>
     *     <li>ends_at_max</li>
     *     <li>times_used</li>
     * </ul>
     *
     * @param query
     * @return
     */
    Future<List<PriceRule>> findPriceRules(JsonObject query);

    Future<PriceRule> findPriceRule(Long priceRuleId);

    Future<Void> deletePriceRule(Long priceRuleId);
    /* End PriceRule Api */

    /* Start DiscountCode Api*/
    Future<DiscountCode> createDiscountCode(Long priceRuleId, String code, int usageCount);

    Future<DiscountCode> updateDiscountCode(DiscountCode discountCode);

    Future<List<DiscountCode>> findDiscountCodes(Long priceRuleId);

    Future<DiscountCode> findDiscountCode(Long priceRuleId, Long discountCodeId);

    default Future<DiscountCode> searchDiscountCode(String code) {
        return searchDiscountCode(code, true);
    }

    Future<DiscountCode> searchDiscountCode(String code, boolean detail);

    Future<Void> deleteDiscountCode(Long priceRuleId, Long discountCodeId);

    Future<DiscountCodeCreation> createDiscountCodes(Long priceRuleId, List<String> codes);

    Future<DiscountCodeCreation> findDiscountCodeCreation(Long priceRuleId, Long batchId);

    Future<List<DiscountCodeResult>> findDiscountCodeResults(Long priceRuleId, Long batchId);
    /* End DiscountCode Api*/

    Future<ProductVariant> findVariant(Long variantId);

    /* Start Webhook Api */
    Future<Webhook> createWebhook(String topic, String address);

    default Future<List<Webhook>> findWebhooks() {
        return findWebhooks(null);
    }

    default Future<List<Webhook>> findWebhooks(int page, int limit) {
        return findWebhooks(new JsonObject().put("page", page).put("limit", limit));
    }

    /**
     * get list webhook by a given query (query can nullable).<br/>
     * supported filter:
     * <ul>
     *     <li>address</li>
     *     <li>created_at_max</li>
     *     <li>created_at_min</li>
     *     <li>fields</li>
     *     <li>limit</li>
     *     <li>page</li>
     *     <li>since_id</li>
     *     <li>topic</li>
     *     <li>updated_at_min</li>
     *     <li>updated_at_max</li>
     * </ul>
     *
     * @param query
     * @return
     */
    Future<List<Webhook>> findWebhooks(JsonObject query);

    default Future<Long> countWebhooks() {
        return countWebhooks(null, null);
    }

    Future<Long> countWebhooks(String address, String topic);

    default Future<Webhook> findWebhook(Long webhookId) {
        return findWebhook(webhookId, null);
    }

    Future<Webhook> findWebhook(Long webhookId, String fields);

    Future<Webhook> updateWebhook(Webhook webhook);

    Future<Void> deleteWebhook(Long webhookId);
    /* End Webhook Api */

    /* Start ScriptTag Api */
    default Future<List<ScriptTag>> findScriptTags() {
        return findScriptTags(null);
    }

    default Future<List<ScriptTag>> findScriptTags(int page, int limit) {
        return findScriptTags(new JsonObject().put("page", page).put("limit", limit));
    }

    /**
     * get list webhook by a given query (query can nullable).<br/>
     * supported filter:
     * <ul>
     *     <li>limit</li>
     *     <li>page</li>
     *     <li>since_id</li>
     *     <li>created_at_min</li>
     *     <li>created_at_max</li>
     *     <li>updated_at_min</li>
     *     <li>updated_at_max</li>
     *     <li>src</li>
     *     <li>fields</li>
     * </ul>
     *
     * @param query
     * @return
     */
    Future<List<ScriptTag>> findScriptTags(JsonObject query);

    default Future<Long> countScriptTags() {
        return countScriptTags(null);
    }

    Future<Long> countScriptTags(String src);

    default Future<ScriptTag> findScriptTag(Long scriptTagId) {
        return findScriptTag(scriptTagId, null);
    }

    Future<ScriptTag> findScriptTag(Long scriptTagId, String fields);

    Future<ScriptTag> createScriptTag(String event, String src);

    Future<ScriptTag> updateScriptTag(ScriptTag scriptTag);

    Future<Void> deleteScriptTag(Long scriptTagId);
    /* End ScriptTag Api */

    /* Start Asset Api */
    default Future<List<Asset>> findAssets(Long themeId) {
        return findAssets(themeId, null);
    }

    Future<List<Asset>> findAssets(Long themeId, String fields);

    default Future<Asset> findAsset(Long themeId, String assetKey) {
        return findAsset(themeId, assetKey, null);
    }

    Future<Asset> findAsset(Long themeId, String assetKey, String fields);

    Future<Asset> updateAsset(Asset asset);

    Future<Asset> uploadAsset(Asset asset, String src);

    Future<Asset> copyAsset(Asset asset, String sourceKey);

    Future<Void> deleteAsset(Long themeId, String assetKey);
    /* End Asset Api */

    /* Start Theme Api */
    default Future<List<Theme>> findThemes() {
        return findThemes(null);
    }

    Future<List<Theme>> findThemes(String fields);

    default Future<Theme> findTheme(Long themeId) {
        return findTheme(themeId, null);
    }

    Future<Theme> findTheme(Long themeId, String fields);

    default Future<Theme> createTheme(String name, String src) {
        return createTheme(name, src, null);
    }

    Future<Theme> createTheme(String name, String src, Theme.Role role);

    Future<Theme> updateTheme(Theme theme);

    Future<Void> deleteTheme(Long themeId);
    /* End Theme Api */

    /* Start Order Api*/
    default Future<List<Order>> findOrders() {
        return findOrders(null);
    }

    default Future<List<Order>> findOrders(int page, int limit) {
        return findOrders(new JsonObject().put("page", page).put("limit", limit));
    }

    /**
     * get list webhook by a given query (query can nullable).<br/>
     * supported filter:
     * <ul>
     *     <li>ids: Retrieve certain orders, specified by a comma-separated list of order IDs.</li>
     *     <li>limit: The maximum number of results to show on a page. (default: 50, maximum: 250)</li>
     *     <li>page: The page of results to show (default: 1)</li>
     *     <li>since_id: Show orders after the specified ID.</li>
     *     <li>created_at_min: Show orders created at or after date (format: 2014-04-25T16:15:47-04:00).</li>
     *     <li>created_at_max: Show orders created at or before date (format: 2014-04-25T16:15:47-04:00).</li>
     *     <li>updated_at_min: Show orders last updated at or after date (format: 2014-04-25T16:15:47-04:00).</li>
     *     <li>updated_at_max: Show orders last updated at or before date (format: 2014-04-25T16:15:47-04:00).</li>
     *     <li>processed_at_min: Show orders imported at or after date (format: 2014-04-25T16:15:47-04:00).</li>
     *     <li>processed_at_max: Show orders imported at or before date (format: 2014-04-25T16:15:47-04:00).</li>
     *     <li>attribution_app_id: Show orders attributed to a certain app, specified by the app ID. Set as current to show orders for the app currently consuming the API.</li>
     *     <li>status: Filter orders by their status.
     * <p>
     * (default: open)
     * open: Show only open orders.
     * closed: Show only closed orders.
     * cancelled: Show only canceled orders.
     * any: Show orders of any status.</li>
     *      <li>financial_status: </li>
     *      <li>fulfillment_status: </li>
     *     <li>fields</li>
     * </ul>
     *
     * @param query
     * @return
     */
    Future<List<Order>> findOrders(JsonObject query);

    default Future<Long> countOrders() {
        return countOrders(null);
    }

    Future<Long> countOrders(JsonObject query);

    default Future<Order> findOrder(Long orderId) {
        return findOrder(orderId, null);
    }

    Future<Order> findOrder(Long orderId, String fields);

    Future<Order> closeOrder(Long orderId);

    Future<Order> openOrder(Long orderId);

    default Future<Order> cancelOrder(Long orderId) {
        return cancelOrder(orderId, null);
    }

    Future<Order> cancelOrder(Long orderId, JsonObject options);

    Future<Order> createOrder(Order order, JsonObject options);

    Future<Order> updateOrder(Order order);

    /**
     * Deletes an order. Orders that interact with an online gateway can't be deleted.
     *
     * @param orderId
     * @return
     */
    Future<Void> deleteOrder(Long orderId);
    /* End Order Api*/

    /* Start Shop Api */
    default Future<Shop> findShop() {
        return findShop(null);
    }

    Future<Shop> findShop(String fields);
    /* End Shop Api */
}
