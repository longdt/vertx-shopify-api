package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.dslplatform.json.JsonValue;

import java.time.OffsetDateTime;

/**
 * Created by thienlong on 24/06/2016.
 */
@CompiledJson
public class Theme {
    private OffsetDateTime createdAt;
    private Long id;
    private String name;
    private boolean previewable;
    private boolean processing;
    private Role role;
    private Long themeStoreId;
    private OffsetDateTime updatedAt;

    public enum Role {
        MAIN("main"), UNPUBLISHED("unpublished"), DEMO("demo");
        private String value;

        Role(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @CompiledJson
        public static Role of(String role) {
            if (MAIN.value.equals(role)) {
                return MAIN;
            } else if (UNPUBLISHED.value.equals(role)) {
                return UNPUBLISHED;
            } else if (DEMO.value.equals(role)) {
                return DEMO;
            }
            return null;
        }
    }
    @JsonAttribute(name = "created_at")
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPreviewable() {
        return previewable;
    }

    public void setPreviewable(boolean previewable) {
        this.previewable = previewable;
    }

    public boolean isProcessing() {
        return processing;
    }

    public void setProcessing(boolean processing) {
        this.processing = processing;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @JsonAttribute(name = "theme_store_id")
    public Long getThemeStoreId() {
        return themeStoreId;
    }

    public void setThemeStoreId(Long themeStoreId) {
        this.themeStoreId = themeStoreId;
    }

    @JsonAttribute(name = "updated_at")
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
