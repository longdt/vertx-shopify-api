package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@Accessors(chain = true)
@CompiledJson
public class DiscountCodeCreation {
    private Long id;
    private Long priceRuleId;
    private OffsetDateTime startedAt;
    private OffsetDateTime completedAt;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String status;
    private Integer codesCount;
    private Integer importedCount;
    private Integer failedCount;

    @JsonAttribute(name = "price_rule_id")
    public Long getPriceRuleId() {
        return priceRuleId;
    }

    @JsonAttribute(name = "started_at")
    public OffsetDateTime getStartedAt() {
        return startedAt;
    }

    @JsonAttribute(name = "completed_at")
    public OffsetDateTime getCompletedAt() {
        return completedAt;
    }

    @JsonAttribute(name = "created_at")
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonAttribute(name = "updated_at")
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    @JsonAttribute(name = "codes_count")
    public Integer getCodesCount() {
        return codesCount;
    }

    @JsonAttribute(name = "imported_count")
    public Integer getImportedCount() {
        return importedCount;
    }

    @JsonAttribute(name = "failed_count")
    public Integer getFailedCount() {
        return failedCount;
    }
}
