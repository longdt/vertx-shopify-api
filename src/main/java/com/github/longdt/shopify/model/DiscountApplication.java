package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
@CompiledJson
public class DiscountApplication {
    private PriceRule.AllocationMethod allocationMethod;
    private String code;
    private String description;
    private PriceRule.TargetSelection targetSelection;
    private PriceRule.TargetType targetType;
    private String title;
    private Type type;
    private BigDecimal value;
    private PriceRule.ValueType valueType;

    @JsonAttribute(name = "allocation_method")
    public PriceRule.AllocationMethod getAllocationMethod() {
        return allocationMethod;
    }

    public void setAllocationMethod(PriceRule.AllocationMethod allocationMethod) {
        this.allocationMethod = allocationMethod;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriceRule.TargetSelection getTargetSelection() {
        return targetSelection;
    }

    public void setTargetSelection(PriceRule.TargetSelection targetSelection) {
        this.targetSelection = targetSelection;
    }

    public PriceRule.TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(PriceRule.TargetType targetType) {
        this.targetType = targetType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public PriceRule.ValueType getValueType() {
        return valueType;
    }

    public void setValueType(PriceRule.ValueType valueType) {
        this.valueType = valueType;
    }

    public enum Type {
        DISCOUNT_CODE("discount_code"), MANUAL("manual"), SCRIPT("script");

        private String value;
        private static Map<String, Type> values =
                Arrays.stream(values()).collect(Collectors.toMap(Type::getValue, Function.identity()));

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Type of(String value) {
            return values.get(value);
        }
    }

}
