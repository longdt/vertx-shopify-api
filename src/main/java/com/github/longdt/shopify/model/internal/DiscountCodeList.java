package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.github.longdt.shopify.model.DiscountCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@CompiledJson
public class DiscountCodeList {
    List<DiscountCode> discountCodes;

    @JsonAttribute(name = "discount_codes")
    public List<DiscountCode> getDiscountCodes() {
        return discountCodes;
    }
}
