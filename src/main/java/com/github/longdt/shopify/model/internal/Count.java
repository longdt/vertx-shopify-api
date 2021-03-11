package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@CompiledJson
public class Count {
    Long count;
}
