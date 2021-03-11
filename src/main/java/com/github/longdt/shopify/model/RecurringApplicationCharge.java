package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.dslplatform.json.JsonValue;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
@CompiledJson
public class RecurringApplicationCharge {
    private OffsetDateTime activatedOn;
    private OffsetDateTime billingOn;
    private OffsetDateTime cancelledOn;
    private BigDecimal cappedAmount;
    private String confirmationUrl;
    private OffsetDateTime createdAt;
    private Long id;
    private String name;
    private BigDecimal price;
    private String returnUrl;
    private Status status;
    private String terms;
    private Boolean test;
    private Integer trialDays;
    private OffsetDateTime trialEndsOn;
    private OffsetDateTime updatedAt;

    @JsonAttribute(name = "activated_on")
    public OffsetDateTime getActivatedOn() {
        return activatedOn;
    }

    @JsonAttribute(name = "billing_on")
    public OffsetDateTime getBillingOn() {
        return billingOn;
    }

    @JsonAttribute(name = "cancelled_on")
    public OffsetDateTime getCancelledOn() {
        return cancelledOn;
    }

    @JsonAttribute(name = "capped_amount")
    public BigDecimal getCappedAmount() {
        return cappedAmount;
    }

    @JsonAttribute(name = "confirmation_url")
    public String getConfirmationUrl() {
        return confirmationUrl;
    }

    @JsonAttribute(name = "created_at")
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonAttribute(name = "return_url")
    public String getReturnUrl() {
        return returnUrl;
    }

    @JsonAttribute(name = "trial_days")
    public Integer getTrialDays() {
        return trialDays;
    }

    @JsonAttribute(name = "trial_ends_on")
    public OffsetDateTime getTrialEndsOn() {
        return trialEndsOn;
    }

    @JsonAttribute(name = "updated_at")
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public enum Status {
        PENDING("pending"),
        ACCEPTED("accepted"),
        ACTIVE("active"),
        DECLINED("declined"),
        EXPIRED("expired"),
        FROZEN("frozen"),
        CANCELLED("cancelled");

        private String value;
        private static Map<String, Status> values =
                Arrays.stream(values()).collect(Collectors.toMap(Status::getValue, Function.identity()));

        Status(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @CompiledJson
        public static Status of(String value) {
            return values.get(value);
        }
    }
}
