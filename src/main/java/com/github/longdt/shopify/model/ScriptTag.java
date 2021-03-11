package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.dslplatform.json.JsonValue;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

/**
 * Created by thienlong on 27/06/2016.
 */
@Data
@Accessors(chain = true)
@CompiledJson
public class ScriptTag {
    private OffsetDateTime createdAt;
    private String event;
    private long id;
    private String src;
    private DisplayScope displayScope;
    private OffsetDateTime updatedAt;

    public enum DisplayScope {
        ONLINE_STORE("online_store"), ORDER_STATUS("online_store"), ALL("all");

        private String value;

        DisplayScope(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @CompiledJson
        public static DisplayScope of(String scope) {
            if (ONLINE_STORE.value.equals(scope)) {
                return ONLINE_STORE;
            } else if (ORDER_STATUS.value.equals(scope)) {
                return ORDER_STATUS;
            } else if (ALL.value.equals(scope)) {
                return ALL;
            }
            return null;
        }
    }

    @JsonAttribute(name = "created_at")
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonAttribute(name = "display_scope")
    public DisplayScope getDisplayScope() {
        return displayScope;
    }

    @JsonAttribute(name = "updated_at")
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}
