package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.github.longdt.shopify.model.DiscountCodeResult;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@CompiledJson
public class DiscountCodeResultList {
    List<DiscountCodeResult> discountCodes;

    @JsonAttribute(name = "discount_codes")
    public List<DiscountCodeResult> getDiscountCodes() {
        return discountCodes;
    }
}
