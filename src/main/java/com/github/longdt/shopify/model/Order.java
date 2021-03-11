package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.dslplatform.json.JsonValue;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
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

    @JsonAttribute(name = "billing_address")
    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    @JsonAttribute(name = "browser_ip")
    public String getBrowserIp() {
        return browserIp;
    }

    @JsonAttribute(name = "buyer_accepts_marketing")
    public Boolean getBuyerAcceptsMarketing() {
        return buyerAcceptsMarketing;
    }

    @JsonAttribute(name = "cancel_reason")
    public CancelReason getCancelReason() {
        return cancelReason;
    }

    @JsonAttribute(name = "cancelled_at")
    public OffsetDateTime getCancelledAt() {
        return cancelledAt;
    }

    @JsonAttribute(name = "cart_token")
    public String getCartToken() {
        return cartToken;
    }

    @JsonAttribute(name = "client_details")
    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    @JsonAttribute(name = "closed_at")
    public OffsetDateTime getClosedAt() {
        return closedAt;
    }

    @JsonAttribute(name = "created_at")
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonAttribute(name = "customer_locale")
    public String getCustomerLocale() {
        return customerLocale;
    }

    public enum CancelReason {
        customer("customer"), fraud("fraud"), inventory("inventory"), declined("declined"), other("other");

        private final String value;
        private static final Map<String, CancelReason> values =
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

    @Data
    @Accessors(chain = true)
    @CompiledJson
    public static class DiscountCode {
        private String code;
        private BigDecimal amount;
        private String type;
    }
}
