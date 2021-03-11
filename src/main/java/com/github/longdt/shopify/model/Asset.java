package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

/**
 * Created by thienlong on 24/06/2016.
 */
@Data
@Accessors(chain = true)
@CompiledJson
public class Asset {
    private String attachment;
    private String contentType;
    private OffsetDateTime createdAt;
    private String key;
    private String publicUrl;
    private int size;
    private long themeId;
    private OffsetDateTime updatedAt;
    private String value;

    @JsonAttribute(name = "content_type")
    public String getContentType() {
        return contentType;
    }

    @JsonAttribute(name = "created_at")
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonAttribute(name = "public_url")
    public String getPublicUrl() {
        return publicUrl;
    }

    @JsonAttribute(name = "theme_id")
    public long getThemeId() {
        return themeId;
    }

    @JsonAttribute(name = "updated_at")
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}
