package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@Accessors(chain = true)
@CompiledJson
public class Shop {
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String countryCode;
    private String countryName;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String customerEmail;
    private String currency;
    private String domain;
    private String email;
    private String googleAppsDomain;
    private Boolean googleAppsLoginEnabled;
    private Long id;
    private Double latitude;
    private Double longitude;
    private String moneyFormat;
    private String moneyWithCurrencyFormat;
    private String weightUnit;
    private String myshopifyDomain;
    private String name;
    private String planName;
    private Boolean hasDiscounts;
    private Boolean hasGiftCards;
    private String planDisplayName;
    private Boolean passwordEnabled;
    private Boolean preLaunchEnabled;
    private String phone;
    private String primaryLocale;
    private String province;
    private String provinceCode;
    private String shopOwner;
    private String source;
    private Boolean forceSsl;
    private Boolean taxShipping;
    private Boolean taxesIncluded;
    private Boolean countryTaxes;
    private String timezone;
    private String ianaTimezone;
    private String zip;
    private Boolean hasStorefront;
    private Boolean setupRequired;
    private Boolean checkoutApiSupported;
    private Boolean multiLocationEnabled;

    @JsonAttribute(name = "country_code")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonAttribute(name = "country_name")
    public String getCountryName() {
        return countryName;
    }

    @JsonAttribute(name = "created_at")
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonAttribute(name = "updated_at")
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    @JsonAttribute(name = "customer_email")
    public String getCustomerEmail() {
        return customerEmail;
    }

    @JsonAttribute(name = "google_apps_domain")
    public String getGoogleAppsDomain() {
        return googleAppsDomain;
    }

    @JsonAttribute(name = "google_apps_login_enabled")
    public Boolean getGoogleAppsLoginEnabled() {
        return googleAppsLoginEnabled;
    }

    @JsonAttribute(name = "money_format")
    public String getMoneyFormat() {
        return moneyFormat;
    }

    @JsonAttribute(name = "money_with_currency_format")
    public String getMoneyWithCurrencyFormat() {
        return moneyWithCurrencyFormat;
    }

    @JsonAttribute(name = "weight_unit")
    public String getWeightUnit() {
        return weightUnit;
    }

    @JsonAttribute(name = "myshopify_domain")
    public String getMyshopifyDomain() {
        return myshopifyDomain;
    }

    @JsonAttribute(name = "plan_name")
    public String getPlanName() {
        return planName;
    }

    @JsonAttribute(name = "has_discounts")
    public Boolean getHasDiscounts() {
        return hasDiscounts;
    }

    @JsonAttribute(name = "has_gift_cards")
    public Boolean getHasGiftCards() {
        return hasGiftCards;
    }

    @JsonAttribute(name = "plan_display_name")
    public String getPlanDisplayName() {
        return planDisplayName;
    }

    @JsonAttribute(name = "password_enabled")
    public Boolean getPasswordEnabled() {
        return passwordEnabled;
    }

    @JsonAttribute(name = "pre_launch_enabled")
    public Boolean getPreLaunchEnabled() {
        return preLaunchEnabled;
    }

    @JsonAttribute(name = "primary_locale")
    public String getPrimaryLocale() {
        return primaryLocale;
    }

    @JsonAttribute(name = "province_code")
    public String getProvinceCode() {
        return provinceCode;
    }

    @JsonAttribute(name = "shop_owner")
    public String getShopOwner() {
        return shopOwner;
    }

    @JsonAttribute(name = "force_ssl")
    public Boolean getForceSsl() {
        return forceSsl;
    }

    @JsonAttribute(name = "tax_shipping")
    public Boolean getTaxShipping() {
        return taxShipping;
    }

    @JsonAttribute(name = "taxes_included")
    public Boolean getTaxesIncluded() {
        return taxesIncluded;
    }

    @JsonAttribute(name = "county_taxes")
    public Boolean getCountryTaxes() {
        return countryTaxes;
    }

    @JsonAttribute(name = "iana_timezone")
    public String getIanaTimezone() {
        return ianaTimezone;
    }

    @JsonAttribute(name = "has_storefront")
    public Boolean getHasStorefront() {
        return hasStorefront;
    }

    @JsonAttribute(name = "setup_required")
    public Boolean getSetupRequired() {
        return setupRequired;
    }

    @JsonAttribute(name = "checkout_api_supported")
    public Boolean getCheckoutApiSupported() {
        return checkoutApiSupported;
    }

    @JsonAttribute(name = "multi_location_enabled")
    public Boolean getMultiLocationEnabled() {
        return multiLocationEnabled;
    }
}
