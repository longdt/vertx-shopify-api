package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.github.longdt.shopify.model.DiscountCode;

@CompiledJson
public class DiscountCodeWrapper {
    private DiscountCode discountCode;

    public DiscountCodeWrapper(DiscountCode discountCode) {
        this.discountCode = discountCode;
    }

    @JsonAttribute(name = "discount_code")
    public DiscountCode getDiscountCode() {
        return discountCode;
    }
}
