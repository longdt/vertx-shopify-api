package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.github.longdt.shopify.model.DiscountCodeCreation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@CompiledJson
public class DiscountCodeCreationWrapper {
    DiscountCodeCreation discountCodeCreation;

    @JsonAttribute(name = "discount_code_creation")
    public DiscountCodeCreation getDiscountCodeCreation() {
        return discountCodeCreation;
    }
}
