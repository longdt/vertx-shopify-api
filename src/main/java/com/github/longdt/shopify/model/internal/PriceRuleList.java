package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.github.longdt.shopify.model.PriceRule;

import java.util.List;

@CompiledJson
public class PriceRuleList {
    private List<PriceRule> priceRules;

    @JsonAttribute(name = "price_rules")
    public List<PriceRule> getPriceRules() {
        return priceRules;
    }

    public void setPriceRules(List<PriceRule> priceRules) {
        this.priceRules = priceRules;
    }
}
