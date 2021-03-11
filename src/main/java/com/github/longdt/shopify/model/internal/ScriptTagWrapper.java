package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.github.longdt.shopify.model.ScriptTag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@CompiledJson
public class ScriptTagWrapper {
    ScriptTag scriptTag;

    @JsonAttribute(name = "script_tag")
    public ScriptTag getScriptTag() {
        return scriptTag;
    }
}
