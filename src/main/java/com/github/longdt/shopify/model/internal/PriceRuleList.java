package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.github.longdt.shopify.model.PriceRule;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@CompiledJson
public class PriceRuleList {
    List<PriceRule> priceRules;

    @JsonAttribute(name = "price_rules")
    public List<PriceRule> getPriceRules() {
        return priceRules;
    }
}
