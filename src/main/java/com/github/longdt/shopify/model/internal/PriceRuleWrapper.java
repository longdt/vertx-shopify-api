package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.github.longdt.shopify.model.PriceRule;
import lombok.Value;

@Value
@CompiledJson
public class PriceRuleWrapper {
    PriceRule priceRule;

    @JsonAttribute(name = "price_rule")
    public PriceRule getPriceRule() {
        return priceRule;
    }
}
