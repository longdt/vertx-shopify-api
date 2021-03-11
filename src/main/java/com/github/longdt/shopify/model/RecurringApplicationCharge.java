package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.dslplatform.json.JsonValue;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public void setActivatedOn(OffsetDateTime activatedOn) {
        this.activatedOn = activatedOn;
    }

    @JsonAttribute(name = "billing_on")
    public OffsetDateTime getBillingOn() {
        return billingOn;
    }

    public void setBillingOn(OffsetDateTime billingOn) {
        this.billingOn = billingOn;
    }

    @JsonAttribute(name = "cancelled_on")
    public OffsetDateTime getCancelledOn() {
        return cancelledOn;
    }

    public void setCancelledOn(OffsetDateTime cancelledOn) {
        this.cancelledOn = cancelledOn;
    }

    @JsonAttribute(name = "capped_amount")
    public BigDecimal getCappedAmount() {
        return cappedAmount;
    }

    public void setCappedAmount(BigDecimal cappedAmount) {
        this.cappedAmount = cappedAmount;
    }

    @JsonAttribute(name = "confirmation_url")
    public String getConfirmationUrl() {
        return confirmationUrl;
    }

    public void setConfirmationUrl(String confirmationUrl) {
        this.confirmationUrl = confirmationUrl;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @JsonAttribute(name = "return_url")
    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public Boolean getTest() {
        return test;
    }

    public void setTest(Boolean test) {
        this.test = test;
    }

    @JsonAttribute(name = "trial_days")
    public Integer getTrialDays() {
        return trialDays;
    }

    public void setTrialDays(Integer trialDays) {
        this.trialDays = trialDays;
    }

    @JsonAttribute(name = "trial_ends_on")
    public OffsetDateTime getTrialEndsOn() {
        return trialEndsOn;
    }

    public void setTrialEndsOn(OffsetDateTime trialEndsOn) {
        this.trialEndsOn = trialEndsOn;
    }

    @JsonAttribute(name = "updated_at")
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
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
