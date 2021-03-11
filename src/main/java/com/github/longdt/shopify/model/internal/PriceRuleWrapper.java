package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.github.longdt.shopify.model.PriceRule;

public class PriceRuleWrapper {
    private PriceRule priceRule;

    @CompiledJson
    public PriceRuleWrapper(PriceRule priceRule) {
        this.priceRule = priceRule;
    }

    @JsonAttribute(name = "price_rule")
    public PriceRule getPriceRule() {
        return priceRule;
    }
}
