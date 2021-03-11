package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.github.longdt.shopify.model.ScriptTag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@CompiledJson
public class ScriptTagList {
    private List<ScriptTag> scriptTags;

    @JsonAttribute(name = "script_tags")
    public List<ScriptTag> getScriptTags() {
        return scriptTags;
    }
}
