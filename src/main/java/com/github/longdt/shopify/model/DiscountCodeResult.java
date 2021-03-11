package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.dslplatform.json.runtime.MapAnalyzer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
@CompiledJson
public class DiscountCodeResult {
    private Long id;
    private String code;
    private Map<String, Object> errors;

    @JsonAttribute(converter = MapAnalyzer.Runtime.class)
    public Map<String, Object> getErrors() {
        return errors;
    }
}
