package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.github.longdt.shopify.model.DiscountCodeResult;

import java.util.List;

@CompiledJson
public class DiscountCodeResultList {
    private List<DiscountCodeResult> discountCodes;

    @JsonAttribute(name = "discount_codes")
    public List<DiscountCodeResult> getDiscountCodes() {
        return discountCodes;
    }

    public void setDiscountCodes(List<DiscountCodeResult> discountCodes) {
        this.discountCodes = discountCodes;
    }
}
