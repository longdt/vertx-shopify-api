package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.github.longdt.shopify.model.Order;

@CompiledJson
public class OrderWrapper {
    private Order order;

    public OrderWrapper(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
