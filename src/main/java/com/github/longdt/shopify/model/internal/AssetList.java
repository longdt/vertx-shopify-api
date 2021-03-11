package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.github.longdt.shopify.model.Asset;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@CompiledJson
public class AssetList {
    List<Asset> assets;
}
