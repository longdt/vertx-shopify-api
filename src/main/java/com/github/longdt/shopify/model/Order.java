package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.dslplatform.json.JsonValue;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@CompiledJson
public class Order {
    private Long appId;
    private BillingAddress billingAddress;
    private String browserIp;
    private Boolean buyerAcceptsMarketing;
    private CancelReason cancelReason;
    private OffsetDateTime cancelledAt;
    private String cartToken;
    private ClientDetails clientDetails;
    private OffsetDateTime closedAt;
    private OffsetDateTime createdAt;
    private String currency;
    private String customerLocale;
    private List<DiscountApplication> discountApplications;
    private List<DiscountCode> discountCodes;
    private Long id;

    @JsonAttribute(name = "app_id")
    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    @JsonAttribute(name = "billing_address")
    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    @JsonAttribute(name = "browser_ip")
    public String getBrowserIp() {
        return browserIp;
    }

    public void setBrowserIp(String browserIp) {
        this.browserIp = browserIp;
    }

    @JsonAttribute(name = "buyer_accepts_marketing")
    public Boolean getBuyerAcceptsMarketing() {
        return buyerAcceptsMarketing;
    }

    public void setBuyerAcceptsMarketing(Boolean buyerAcceptsMarketing) {
        this.buyerAcceptsMarketing = buyerAcceptsMarketing;
    }

    @JsonAttribute(name = "cancel_reason")
    public CancelReason getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(CancelReason cancelReason) {
        this.cancelReason = cancelReason;
    }

    @JsonAttribute(name = "cancelled_at")
    public OffsetDateTime getCancelledAt() {
        return cancelledAt;
    }

    public void setCancelledAt(OffsetDateTime cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    @JsonAttribute(name = "cart_token")
    public String getCartToken() {
        return cartToken;
    }

    public void setCartToken(String cartToken) {
        this.cartToken = cartToken;
    }

    @JsonAttribute(name = "client_details")
    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    @JsonAttribute(name = "closed_at")
    public OffsetDateTime getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(OffsetDateTime closedAt) {
        this.closedAt = closedAt;
    }

    @JsonAttribute(name = "created_at")
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonAttribute(name = "customer_locale")
    public String getCustomerLocale() {
        return customerLocale;
    }

    public void setCustomerLocale(String customerLocale) {
        this.customerLocale = customerLocale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public enum CancelReason {
        customer("customer"), fraud("fraud"), inventory("inventory"), declined("declined"), other("other");

        private String value;
        private static Map<String, CancelReason> values =
                Arrays.stream(values()).collect(Collectors.toMap(CancelReason::getValue, Function.identity()));

        CancelReason(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        public static CancelReason of(String value) {
            return values.get(value);
        }
    }

    public static class DiscountCode {
        private String code;
        private BigDecimal amount;
    }
}
