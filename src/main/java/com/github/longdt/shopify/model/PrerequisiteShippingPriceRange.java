package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

import java.math.BigDecimal;

@CompiledJson
public class PrerequisiteShippingPriceRange {
    private BigDecimal lessThanOrEqualTo;

    @JsonAttribute(name = "less_than_or_equal_to")
    public BigDecimal getLessThanOrEqualTo() {
        return lessThanOrEqualTo;
    }

    public void setLessThanOrEqualTo(BigDecimal lessThanOrEqualTo) {
        this.lessThanOrEqualTo = lessThanOrEqualTo;
    }
}
