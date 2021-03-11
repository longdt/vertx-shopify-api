package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@CompiledJson
public class PrerequisiteShippingPriceRange {
    private BigDecimal lessThanOrEqualTo;

    @JsonAttribute(name = "less_than_or_equal_to")
    public BigDecimal getLessThanOrEqualTo() {
        return lessThanOrEqualTo;
    }
}
