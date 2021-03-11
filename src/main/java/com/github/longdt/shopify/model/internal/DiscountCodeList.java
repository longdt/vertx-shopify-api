package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.github.longdt.shopify.model.DiscountCode;

import java.util.List;

@CompiledJson
public class DiscountCodeList {
    private List<DiscountCode> discountCodes;

    @JsonAttribute(name = "discount_codes")
    public List<DiscountCode> getDiscountCodes() {
        return discountCodes;
    }

    public void setDiscountCodes(List<DiscountCode> discountCodes) {
        this.discountCodes = discountCodes;
    }
}
