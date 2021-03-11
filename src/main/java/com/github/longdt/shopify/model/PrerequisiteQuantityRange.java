package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@CompiledJson
public class PrerequisiteQuantityRange {
    private Integer greaterThanOrEqualTo;

    @JsonAttribute(name = "greater_than_or_equal_to")
    public Integer getGreaterThanOrEqualTo() {
        return greaterThanOrEqualTo;
    }
}
