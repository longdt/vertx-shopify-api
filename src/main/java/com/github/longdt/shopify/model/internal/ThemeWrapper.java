package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.github.longdt.shopify.model.Theme;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@CompiledJson
public class ThemeWrapper {
    Theme theme;
}

