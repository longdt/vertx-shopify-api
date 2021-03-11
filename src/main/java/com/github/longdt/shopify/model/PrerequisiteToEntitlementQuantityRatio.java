package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

@CompiledJson
public class PrerequisiteToEntitlementQuantityRatio {
    private Integer prerequisiteQuantity;
    private Integer entitledQuantity;

    @JsonAttribute(name = "prerequisite_quantity")
    public Integer getPrerequisiteQuantity() {
        return prerequisiteQuantity;
    }

    public void setPrerequisiteQuantity(Integer prerequisiteQuantity) {
        this.prerequisiteQuantity = prerequisiteQuantity;
    }

    @JsonAttribute(name = "entitled_quantity")
    public Integer getEntitledQuantity() {
        return entitledQuantity;
    }

    public void setEntitledQuantity(Integer entitledQuantity) {
        this.entitledQuantity = entitledQuantity;
    }
}
