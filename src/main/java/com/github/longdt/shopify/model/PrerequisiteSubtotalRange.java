package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

import java.math.BigDecimal;

@CompiledJson
public class PrerequisiteSubtotalRange {
    private BigDecimal greaterThanOrEqualTo;

    @JsonAttribute(name = "greater_than_or_equal_to")
    public BigDecimal getGreaterThanOrEqualTo() {
        return greaterThanOrEqualTo;
    }

    public void setGreaterThanOrEqualTo(BigDecimal greaterThanOrEqualTo) {
        this.greaterThanOrEqualTo = greaterThanOrEqualTo;
    }
}
