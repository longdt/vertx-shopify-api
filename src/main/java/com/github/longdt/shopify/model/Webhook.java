package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by thienlong on 24/06/2016.
 */
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonAttribute(name = "created_at")
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonAttribute(name = "metafield_namespaces")
    public List<String> getMetafieldNamespaces() {
        return metafieldNamespaces;
    }

    public void setMetafieldNamespaces(List<String> metafieldNamespaces) {
        this.metafieldNamespaces = metafieldNamespaces;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @JsonAttribute(name = "updated_at")
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
