package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.dslplatform.json.JsonValue;

import java.time.OffsetDateTime;

/**
 * Created by thienlong on 27/06/2016.
 */
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

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @JsonAttribute(name = "display_scope")
    public DisplayScope getDisplayScope() {
        return displayScope;
    }

    public void setDisplayScope(DisplayScope displayScope) {
        this.displayScope = displayScope;
    }

    @JsonAttribute(name = "updated_at")
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
