package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.github.longdt.shopify.model.Theme;

@CompiledJson
public class ThemeWrapper {
    private Theme theme;

    public ThemeWrapper(Theme theme) {
        this.theme = theme;
    }

    public Theme getTheme() {
        return theme;
    }
}

