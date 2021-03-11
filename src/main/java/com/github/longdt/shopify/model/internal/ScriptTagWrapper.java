package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.github.longdt.shopify.model.ScriptTag;

@CompiledJson
public class ScriptTagWrapper {
    private ScriptTag scriptTag;

    public ScriptTagWrapper(ScriptTag scriptTag) {
        this.scriptTag = scriptTag;
    }

    @JsonAttribute(name = "script_tag")
    public ScriptTag getScriptTag() {
        return scriptTag;
    }
}
