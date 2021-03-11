package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@CompiledJson
public class PrerequisiteToEntitlementQuantityRatio {
    private Integer prerequisiteQuantity;
    private Integer entitledQuantity;

    @JsonAttribute(name = "prerequisite_quantity")
    public Integer getPrerequisiteQuantity() {
        return prerequisiteQuantity;
    }

    @JsonAttribute(name = "entitled_quantity")
    public Integer getEntitledQuantity() {
        return entitledQuantity;
    }
}
