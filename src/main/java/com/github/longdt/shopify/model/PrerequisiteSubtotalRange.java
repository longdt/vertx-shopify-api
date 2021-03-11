package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@CompiledJson
public class PrerequisiteSubtotalRange {
    private BigDecimal greaterThanOrEqualTo;

    @JsonAttribute(name = "greater_than_or_equal_to")
    public BigDecimal getGreaterThanOrEqualTo() {
        return greaterThanOrEqualTo;
    }
}
