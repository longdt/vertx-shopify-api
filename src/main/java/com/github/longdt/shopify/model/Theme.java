package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.dslplatform.json.JsonValue;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

/**
 * Created by thienlong on 24/06/2016.
 */
@Data
@Accessors(chain = true)
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

    @JsonAttribute(name = "theme_store_id")
    public Long getThemeStoreId() {
        return themeStoreId;
    }

    @JsonAttribute(name = "updated_at")
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}
