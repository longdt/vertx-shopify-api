package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.dslplatform.json.JsonValue;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@CompiledJson
public class PriceRule {
    private AllocationMethod allocationMethod;
    private OffsetDateTime createdAt;
    private CustomerSelection customerSelection;
    private OffsetDateTime endsAt;
    private List<Long> entitledCollectionIds;
    private List<Long> entitledCountryIds;
    private List<Long> entitledProductIds;
    private List<Long> entitledVariantIds;
    private Long id;
    private Boolean oncePerCustomer;
    private List<Long> prerequisiteCustomerIds;
    private PrerequisiteQuantityRange prerequisiteQuantityRange;
    private List<Long> prerequisiteSavedSearchIds;
    private PrerequisiteShippingPriceRange prerequisiteShippingPriceRange;
    private PrerequisiteSubtotalRange prerequisiteSubtotalRange;
    private OffsetDateTime startsAt;
    private TargetSelection targetSelection;
    private TargetType targetType;
    private String title;
    private Integer usageLimit;
    private List<Long> prerequisiteProductIds;
    private List<Long> prerequisiteVariantIds;
    private List<Long> prerequisiteCollectionIds;
    private BigDecimal value;
    private ValueType valueType;
    private PrerequisiteToEntitlementQuantityRatio prerequisiteToEntitlementQuantityRatio;
    private Integer allocationLimit;

    private OffsetDateTime updatedAt;

    public enum AllocationMethod {
        EACH("each"), ACROSS("across");

        private String value;

        AllocationMethod(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @CompiledJson
        public static AllocationMethod of(String value) {
            if (EACH.value.equals(value)) {
                return EACH;
            } else if (ACROSS.value.equals(value)) {
                return ACROSS;
            }
            return null;
        }
    }

    public enum CustomerSelection {
        ALL("all"), PREREQUISITE("prerequisite");

        private String value;

        CustomerSelection(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @CompiledJson
        public static CustomerSelection of(String value) {
            if (ALL.value.equals(value)) {
                return ALL;
            } else if (PREREQUISITE.value.equals(value)) {
                return PREREQUISITE;
            }
            return null;
        }
    }

    public enum TargetSelection {
        ALL("all"), ENTITLED("entitled");

        private String value;

        TargetSelection(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @CompiledJson
        public static TargetSelection of(String value) {
            if (ALL.value.equals(value)) {
                return ALL;
            } else if (ENTITLED.value.equals(value)) {
                return ENTITLED;
            }
            return null;
        }
    }

    public enum TargetType {
        LINE_ITEM("line_item"), SHIPPING_LINE("shipping_line");

        private String value;

        TargetType(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @CompiledJson
        public static TargetType of(String value) {
            if (LINE_ITEM.value.equals(value)) {
                return LINE_ITEM;
            } else if (SHIPPING_LINE.value.equals(value)) {
                return SHIPPING_LINE;
            }
            return null;
        }
    }

    public enum ValueType {
        FIXED_AMOUNT("fixed_amount"), PERCENTAGE("percentage");

        private String value;

        ValueType(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @CompiledJson
        public static ValueType of(String value) {
            if (FIXED_AMOUNT.value.equals(value)) {
                return FIXED_AMOUNT;
            } else if (PERCENTAGE.value.equals(value)) {
                return PERCENTAGE;
            }
            return null;
        }
    }

    @JsonAttribute(name = "allocation_method")
    public AllocationMethod getAllocationMethod() {
        return allocationMethod;
    }

    public void setAllocationMethod(AllocationMethod allocationMethod) {
        this.allocationMethod = allocationMethod;
    }

    @JsonAttribute(name = "created_at")
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @JsonAttribute(name = "customer_selection")
    public CustomerSelection getCustomerSelection() {
        return customerSelection;
    }

    public void setCustomerSelection(CustomerSelection customerSelection) {
        this.customerSelection = customerSelection;
    }

    @JsonAttribute(name = "ends_at")
    public OffsetDateTime getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(OffsetDateTime endsAt) {
        this.endsAt = endsAt;
    }

    @JsonAttribute(name = "entitled_collection_ids")
    public List<Long> getEntitledCollectionIds() {
        return entitledCollectionIds;
    }

    public void setEntitledCollectionIds(List<Long> entitledCollectionIds) {
        this.entitledCollectionIds = entitledCollectionIds;
    }

    @JsonAttribute(name = "entitled_country_ids")
    public List<Long> getEntitledCountryIds() {
        return entitledCountryIds;
    }

    public void setEntitledCountryIds(List<Long> entitledCountryIds) {
        this.entitledCountryIds = entitledCountryIds;
    }

    @JsonAttribute(name = "entitled_product_ids")
    public List<Long> getEntitledProductIds() {
        return entitledProductIds;
    }

    public void setEntitledProductIds(List<Long> entitledProductIds) {
        this.entitledProductIds = entitledProductIds;
    }

    @JsonAttribute(name = "entitled_variant_ids")
    public List<Long> getEntitledVariantIds() {
        return entitledVariantIds;
    }

    public void setEntitledVariantIds(List<Long> entitledVariantIds) {
        this.entitledVariantIds = entitledVariantIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonAttribute(name = "once_per_customer")
    public Boolean getOncePerCustomer() {
        return oncePerCustomer;
    }

    public void setOncePerCustomer(Boolean oncePerCustomer) {
        this.oncePerCustomer = oncePerCustomer;
    }

    @JsonAttribute(name = "prerequisite_customer_ids")
    public List<Long> getPrerequisiteCustomerIds() {
        return prerequisiteCustomerIds;
    }

    public void setPrerequisiteCustomerIds(List<Long> prerequisiteCustomerIds) {
        this.prerequisiteCustomerIds = prerequisiteCustomerIds;
    }

    @JsonAttribute(name = "prerequisite_quantity_range")
    public PrerequisiteQuantityRange getPrerequisiteQuantityRange() {
        return prerequisiteQuantityRange;
    }

    public void setPrerequisiteQuantityRange(PrerequisiteQuantityRange prerequisiteQuantityRange) {
        this.prerequisiteQuantityRange = prerequisiteQuantityRange;
    }

    @JsonAttribute(name = "prerequisite_saved_search_ids")
    public List<Long> getPrerequisiteSavedSearchIds() {
        return prerequisiteSavedSearchIds;
    }

    public void setPrerequisiteSavedSearchIds(List<Long> prerequisiteSavedSearchIds) {
        this.prerequisiteSavedSearchIds = prerequisiteSavedSearchIds;
    }

    @JsonAttribute(name = "prerequisite_shipping_price_range")
    public PrerequisiteShippingPriceRange getPrerequisiteShippingPriceRange() {
        return prerequisiteShippingPriceRange;
    }

    public void setPrerequisiteShippingPriceRange(PrerequisiteShippingPriceRange prerequisiteShippingPriceRange) {
        this.prerequisiteShippingPriceRange = prerequisiteShippingPriceRange;
    }

    @JsonAttribute(name = "prerequisite_subtotal_range")
    public PrerequisiteSubtotalRange getPrerequisiteSubtotalRange() {
        return prerequisiteSubtotalRange;
    }

    public void setPrerequisiteSubtotalRange(PrerequisiteSubtotalRange prerequisiteSubtotalRange) {
        this.prerequisiteSubtotalRange = prerequisiteSubtotalRange;
    }

    @JsonAttribute(name = "starts_at")
    public OffsetDateTime getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(OffsetDateTime startsAt) {
        this.startsAt = startsAt;
    }

    @JsonAttribute(name = "target_selection")
    public TargetSelection getTargetSelection() {
        return targetSelection;
    }

    public void setTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection;
    }

    @JsonAttribute(name = "target_type")
    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonAttribute(name = "usage_limit")
    public Integer getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(Integer usageLimit) {
        this.usageLimit = usageLimit;
    }

    @JsonAttribute(name = "prerequisite_product_ids")
    public List<Long> getPrerequisiteProductIds() {
        return prerequisiteProductIds;
    }

    public void setPrerequisiteProductIds(List<Long> prerequisiteProductIds) {
        this.prerequisiteProductIds = prerequisiteProductIds;
    }

    @JsonAttribute(name = "prerequisite_variant_ids")
    public List<Long> getPrerequisiteVariantIds() {
        return prerequisiteVariantIds;
    }

    public void setPrerequisiteVariantIds(List<Long> prerequisiteVariantIds) {
        this.prerequisiteVariantIds = prerequisiteVariantIds;
    }

    @JsonAttribute(name = "prerequisite_collection_ids")
    public List<Long> getPrerequisiteCollectionIds() {
        return prerequisiteCollectionIds;
    }

    public void setPrerequisiteCollectionIds(List<Long> prerequisiteCollectionIds) {
        this.prerequisiteCollectionIds = prerequisiteCollectionIds;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @JsonAttribute(name = "value_type")
    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    @JsonAttribute(name = "prerequisite_to_entitlement_quantity_ratio")
    public PrerequisiteToEntitlementQuantityRatio getPrerequisiteToEntitlementQuantityRatio() {
        return prerequisiteToEntitlementQuantityRatio;
    }

    public void setPrerequisiteToEntitlementQuantityRatio(PrerequisiteToEntitlementQuantityRatio prerequisiteToEntitlementQuantityRatio) {
        this.prerequisiteToEntitlementQuantityRatio = prerequisiteToEntitlementQuantityRatio;
    }

    @JsonAttribute(name = "allocation_limit")
    public Integer getAllocationLimit() {
        return allocationLimit;
    }

    public void setAllocationLimit(Integer allocationLimit) {
        this.allocationLimit = allocationLimit;
    }

    @JsonAttribute(name = "updated_at")
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
