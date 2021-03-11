package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.github.longdt.shopify.model.DiscountCodeCreation;

@CompiledJson
public class DiscountCodeCreationWrapper {
    private DiscountCodeCreation discountCodeCreation;

    public DiscountCodeCreationWrapper(DiscountCodeCreation discountCodeCreation) {
        this.discountCodeCreation = discountCodeCreation;
    }

    @JsonAttribute(name = "discount_code_creation")
    public DiscountCodeCreation getDiscountCodeCreation() {
        return discountCodeCreation;
    }
}
