package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.github.longdt.shopify.model.ScriptTag;

import java.util.List;

@CompiledJson
public class ScriptTagList {
    private List<ScriptTag> scriptTags;

    @JsonAttribute(name = "script_tags")
    public List<ScriptTag> getScriptTags() {
        return scriptTags;
    }

    public void setScriptTags(List<ScriptTag> scriptTags) {
        this.scriptTags = scriptTags;
    }
}
