package com.github.longdt.shopify.client;

import com.github.longdt.shopify.model.Asset;
import com.github.longdt.shopify.model.Theme;
import com.github.longdt.shopify.model.internal.AssetWrapper;
import com.github.longdt.shopify.util.Futures;
import com.github.longdt.shopify.util.Json;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SessionTest {
    private ShopifyClient shopifyClient = new ShopifyClientImpl(Vertx.vertx(), "6c1a338b2a7047c9771aa2a3d19ed587", "389af99afbe007fd6d6c3c43457b718a");
    private static String jsonBody = "{\"asset\":{\"updated_at\":\"2018-09-27T04:54:18-04:00\",\"key\":\"layout/theme.liquid\",\"value\":\"<!doctype html>\\n<!--[if IE 9]> <html class=\\\"ie9 no-js\\\" lang=\\\"{{ shop.locale }}\\\"> <![endif]-->\\n<!--[if (gt IE 9)|!(IE)]><!--> <html class=\\\"no-js\\\" lang=\\\"{{ shop.locale }}\\\"> <!--<![endif]-->\\n<head>\\n  <meta charset=\\\"utf-8\\\">\\n  <meta http-equiv=\\\"X-UA-Compatible\\\" content=\\\"IE=edge,chrome=1\\\">\\n  <meta name=\\\"viewport\\\" content=\\\"width=device-width,initial-scale=1\\\">\\n  <meta name=\\\"theme-color\\\" content=\\\"{{ settings.color_button }}\\\">\\n  <link rel=\\\"canonical\\\" href=\\\"{{ canonical_url }}\\\">\\n\\n  {%- if settings.favicon != blank -%}\\n    <link rel=\\\"shortcut icon\\\" href=\\\"{{ settings.favicon | img_url: '32x32' }}\\\" type=\\\"image/png\\\">\\n  {%- endif -%}\\n\\n  {%- capture seo_title -%}\\n    {%- if template == 'search' and search.performed == true -%}\\n      {{ 'general.search.heading' | t: count: search.results_count }}: {{ 'general.search.results_with_count' | t: terms: search.terms, count: search.results_count }}\\n    {%- else -%}\\n      {{ page_title }}\\n    {%- endif -%}\\n    {%- if current_tags -%}\\n      {%- assign meta_tags = current_tags | join: ', ' -%} &ndash; {{ 'general.meta.tags' | t: tags: meta_tags -}}\\n    {%- endif -%}\\n    {%- if current_page != 1 -%}\\n      &ndash; {{ 'general.meta.page' | t: page: current_page }}\\n    {%- endif -%}\\n    {%- assign escaped_page_title = page_title | escape -%}\\n    {%- unless escaped_page_title contains shop.name -%}\\n      &ndash; {{ shop.name }}\\n    {%- endunless -%}\\n  {%- endcapture -%}\\n  <title>{{ seo_title | strip }}</title>\\n\\n  {%- if page_description -%}\\n    <meta name=\\\"description\\\" content=\\\"{{ page_description | escape }}\\\">\\n  {%- endif -%}\\n\\n  {% include 'social-meta-tags' %}\\n\\n  {{ 'theme.scss.css' | asset_url | stylesheet_tag }}\\n\\n  <script>\\n    var theme = {\\n      strings: {\\n        addToCart: {{ 'products.product.add_to_cart' | t | json }},\\n        soldOut: {{ 'products.product.sold_out' | t | json }},\\n        unavailable: {{ 'products.product.unavailable' | t | json }},\\n        regularPrice: {{ 'products.product.regular_price' | t | json }},\\n        sale: {{ 'products.product.on_sale' | t | json }},\\n        showMore: {{ 'general.filters.show_more' | t | json }},\\n        showLess: {{ 'general.filters.show_less' | t | json }},\\n        addressError: {{ 'sections.map.address_error' | t | json }},\\n        addressNoResults: {{ 'sections.map.address_no_results' | t | json }},\\n        addressQueryLimit: {{ 'sections.map.address_query_limit_html' | t | json }},\\n        authError: {{ 'sections.map.auth_error_html' | t | json }},\\n        newWindow: {{ 'general.accessibility.link_messages.new_window' | t | json }},\\n        external: {{ 'general.accessibility.link_messages.external' | t | json }},\\n        newWindowExternal: {{ 'general.accessibility.link_messages.new_window_and_external' | t | json }}\\n      },\\n      moneyFormat: {{ shop.money_format | json }}\\n    }\\n\\n    document.documentElement.className = document.documentElement.className.replace('no-js', 'js');\\n  </script>\\n\\n  <!--[if (lte IE 9) ]>{{ 'match-media.min.js' | asset_url | script_tag }}<![endif]-->\\n\\n  {%- if template.directory == 'customers' -%}\\n    <!--[if (gt IE 9)|!(IE)]><!--><script src=\\\"{{ 'shopify_common.js' | shopify_asset_url }}\\\" defer=\\\"defer\\\"></script><!--<![endif]-->\\n    <!--[if lte IE 9]><script src=\\\"{{ 'shopify_common.js' | shopify_asset_url }}\\\"></script><![endif]-->\\n  {%- endif -%}\\n\\n  <!--[if (gt IE 9)|!(IE)]><!--><script src=\\\"{{ 'lazysizes.js' | asset_url }}\\\" async=\\\"async\\\"></script><!--<![endif]-->\\n  <!--[if lte IE 9]><script src=\\\"{{ 'lazysizes.min.js' | asset_url }}\\\"></script><![endif]-->\\n\\n  <!--[if (gt IE 9)|!(IE)]><!--><script src=\\\"{{ 'vendor.js' | asset_url }}\\\" defer=\\\"defer\\\"></script><!--<![endif]-->\\n  <!--[if lte IE 9]><script src=\\\"{{ 'vendor.js' | asset_url }}\\\"></script><![endif]-->\\n\\n  <!--[if (gt IE 9)|!(IE)]><!--><script src=\\\"{{ 'theme.js' | asset_url }}\\\" defer=\\\"defer\\\"></script><!--<![endif]-->\\n  <!--[if lte IE 9]><script src=\\\"{{ 'theme.js' | asset_url }}\\\"></script><![endif]-->\\n\\n  {{ content_for_header }}\\n</head>\\n\\n<body class=\\\"template-{{ template | split: '.' | first }}\\\">\\n\\n  <a class=\\\"in-page-link visually-hidden skip-link\\\" href=\\\"#MainContent\\\">{{ 'general.accessibility.skip_to_content' | t }}</a>\\n\\n  <div id=\\\"SearchDrawer\\\" class=\\\"search-bar drawer drawer--top\\\" role=\\\"dialog\\\" aria-modal=\\\"true\\\" aria-label=\\\"{{ 'general.search.placeholder' | t }}\\\">\\n    <div class=\\\"search-bar__table\\\">\\n      <div class=\\\"search-bar__table-cell search-bar__form-wrapper\\\">\\n        <form class=\\\"search search-bar__form\\\" action=\\\"/search\\\" method=\\\"get\\\" role=\\\"search\\\">\\n          <input class=\\\"search__input search-bar__input\\\" type=\\\"search\\\" name=\\\"q\\\" value=\\\"{{ search.terms | escape }}\\\" placeholder=\\\"{{ 'general.search.placeholder' | t }}\\\" aria-label=\\\"{{ 'general.search.placeholder' | t }}\\\">\\n          <button class=\\\"search-bar__submit search__submit btn--link\\\" type=\\\"submit\\\">\\n            {% include 'icon-search' %}\\n            <span class=\\\"icon__fallback-text\\\">{{ 'general.search.submit' | t }}</span>\\n          </button>\\n        </form>\\n      </div>\\n      <div class=\\\"search-bar__table-cell text-right\\\">\\n        <button type=\\\"button\\\" class=\\\"btn--link search-bar__close js-drawer-close\\\">\\n          {% include 'icon-close' %}\\n          <span class=\\\"icon__fallback-text\\\">{{ 'general.search.close' | t }}</span>\\n        </button>\\n      </div>\\n    </div>\\n  </div>\\n\\n  {% section 'header' %}\\n\\n  <div class=\\\"page-container\\\" id=\\\"PageContainer\\\">\\n    <div id=\\\"luckywheel-div\\\" style=\\\"transition: all 0.7s ease-out 0s; z-index: 2147483647; transform: translateX(0px);\\\">\\n        <iframe id=\\\"luckywheel-iframe\\\" frameborder=\\\"0\\\" src=\\\"https://longdt.foxpify.com/static/lucky_wheel.htm\\\" style=\\\"width: 800px;height: 600px;\\\"></iframe>\\n    </div>\\n\\n    <main class=\\\"main-content js-focus-hidden\\\" id=\\\"MainContent\\\" role=\\\"main\\\" tabindex=\\\"-1\\\">\\n      {{ content_for_layout }}\\n    </main>\\n\\n    {% section 'footer' %}\\n\\n  </div>\\n\\n  <ul hidden>\\n    <li id=\\\"a11y-refresh-page-message\\\">{{ 'general.accessibility.refresh_page' | t }}</li>\\n  </ul>\\n</body>\\n</html>\\n\",\"content_type\":\"text/x-liquid\",\"theme_id\":38374506566,\"public_url\":null,\"size\":5670,\"attachment\":null,\"created_at\":\"2018-09-26T07:17:01-04:00\"}}";

    @Test
    public void updateAsset() {
        Session session = shopifyClient.newSession("lucky-wheel-demo.myshopify.com", "4f124e0cbcd59d51b8c8b0822856680c");
        Asset asset = Json.decodeValue(Buffer.buffer(jsonBody), AssetWrapper.class).getAsset();
        asset = Futures.join(session.updateAsset(asset));
        List<Theme> themes = Futures.join(session.findThemes());
        System.out.println("count themes: " + themes.size() + " " + themes.get(0).getName());
        Long themeId = themes.get(0).getId();
        List<Asset> assets = Futures.join(session.findAssets(themeId));
        assets.forEach(a -> System.out.println(a.getKey()));
    }
}