package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.github.longdt.shopify.model.Shop;

@CompiledJson
public class ShopWrapper {
    private Shop shop;

    public ShopWrapper(Shop shop) {
        this.shop = shop;
    }

    public Shop getShop() {
        return shop;
    }
}
