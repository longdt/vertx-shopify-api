package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by thienlong on 24/06/2016.
 */
@Data
@Accessors(chain = true)
@CompiledJson
public class Webhook {
    private String address;
    private OffsetDateTime createdAt;
    private List<String> fields;
    private String format;
    private Long id;
    private List<String> metafieldNamespaces;
    private String topic;
    private OffsetDateTime updatedAt;

    @JsonAttribute(name = "created_at")
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonAttribute(name = "metafield_namespaces")
    public List<String> getMetafieldNamespaces() {
        return metafieldNamespaces;
    }

    @JsonAttribute(name = "updated_at")
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}
