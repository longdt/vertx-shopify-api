package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.github.longdt.shopify.model.DiscountCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@CompiledJson
public class DiscountCodeWrapper {
    DiscountCode discountCode;

    @JsonAttribute(name = "discount_code")
    public DiscountCode getDiscountCode() {
        return discountCode;
    }
}
