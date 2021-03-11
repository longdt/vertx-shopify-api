package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@Accessors(chain = true)
@CompiledJson
public class DiscountCode {
    private String code;
    private OffsetDateTime createdAt;
    private Long id;
    private Long priceRuleId;
    private Integer usageCount;
    private OffsetDateTime updatedAt;

    @JsonAttribute(name = "created_at")
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonAttribute(name = "price_rule_id")
    public Long getPriceRuleId() {
        return priceRuleId;
    }

    @JsonAttribute(name = "usage_count")
    public Integer getUsageCount() {
        return usageCount;
    }

    @JsonAttribute(name = "updated_at")
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}
