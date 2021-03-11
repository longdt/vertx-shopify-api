package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.github.longdt.shopify.model.Asset;
import lombok.Value;

@Value
@CompiledJson
public class AssetWrapper {
    Asset asset;
}
