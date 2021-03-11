package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.github.longdt.shopify.model.Order;
import lombok.Value;

@Value
@CompiledJson
public class OrderWrapper {
    Order order;
}
