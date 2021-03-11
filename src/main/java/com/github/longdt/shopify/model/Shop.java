package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

import java.time.OffsetDateTime;

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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @JsonAttribute(name = "country_code")
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonAttribute(name = "country_name")
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @JsonAttribute(name = "created_at")
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @JsonAttribute(name = "updated_at")
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonAttribute(name = "customer_email")
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonAttribute(name = "google_apps_domain")
    public String getGoogleAppsDomain() {
        return googleAppsDomain;
    }

    public void setGoogleAppsDomain(String googleAppsDomain) {
        this.googleAppsDomain = googleAppsDomain;
    }

    @JsonAttribute(name = "google_apps_login_enabled")
    public Boolean getGoogleAppsLoginEnabled() {
        return googleAppsLoginEnabled;
    }

    public void setGoogleAppsLoginEnabled(Boolean googleAppsLoginEnabled) {
        this.googleAppsLoginEnabled = googleAppsLoginEnabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @JsonAttribute(name = "money_format")
    public String getMoneyFormat() {
        return moneyFormat;
    }

    public void setMoneyFormat(String moneyFormat) {
        this.moneyFormat = moneyFormat;
    }

    @JsonAttribute(name = "money_with_currency_format")
    public String getMoneyWithCurrencyFormat() {
        return moneyWithCurrencyFormat;
    }

    public void setMoneyWithCurrencyFormat(String moneyWithCurrencyFormat) {
        this.moneyWithCurrencyFormat = moneyWithCurrencyFormat;
    }

    @JsonAttribute(name = "weight_unit")
    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    @JsonAttribute(name = "myshopify_domain")
    public String getMyshopifyDomain() {
        return myshopifyDomain;
    }

    public void setMyshopifyDomain(String myshopifyDomain) {
        this.myshopifyDomain = myshopifyDomain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonAttribute(name = "plan_name")
    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    @JsonAttribute(name = "has_discounts")
    public Boolean getHasDiscounts() {
        return hasDiscounts;
    }

    public void setHasDiscounts(Boolean hasDiscounts) {
        this.hasDiscounts = hasDiscounts;
    }

    @JsonAttribute(name = "has_gift_cards")
    public Boolean getHasGiftCards() {
        return hasGiftCards;
    }

    public void setHasGiftCards(Boolean hasGiftCards) {
        this.hasGiftCards = hasGiftCards;
    }

    @JsonAttribute(name = "plan_display_name")
    public String getPlanDisplayName() {
        return planDisplayName;
    }

    public void setPlanDisplayName(String planDisplayName) {
        this.planDisplayName = planDisplayName;
    }

    @JsonAttribute(name = "password_enabled")
    public Boolean getPasswordEnabled() {
        return passwordEnabled;
    }

    public void setPasswordEnabled(Boolean passwordEnabled) {
        this.passwordEnabled = passwordEnabled;
    }

    @JsonAttribute(name = "pre_launch_enabled")
    public Boolean getPreLaunchEnabled() {
        return preLaunchEnabled;
    }

    public void setPreLaunchEnabled(Boolean preLaunchEnabled) {
        this.preLaunchEnabled = preLaunchEnabled;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonAttribute(name = "primary_locale")
    public String getPrimaryLocale() {
        return primaryLocale;
    }

    public void setPrimaryLocale(String primaryLocale) {
        this.primaryLocale = primaryLocale;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @JsonAttribute(name = "province_code")
    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    @JsonAttribute(name = "shop_owner")
    public String getShopOwner() {
        return shopOwner;
    }

    public void setShopOwner(String shopOwner) {
        this.shopOwner = shopOwner;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @JsonAttribute(name = "force_ssl")
    public Boolean getForceSsl() {
        return forceSsl;
    }

    public void setForceSsl(Boolean forceSsl) {
        this.forceSsl = forceSsl;
    }

    @JsonAttribute(name = "tax_shipping")
    public Boolean getTaxShipping() {
        return taxShipping;
    }

    public void setTaxShipping(Boolean taxShipping) {
        this.taxShipping = taxShipping;
    }

    @JsonAttribute(name = "taxes_included")
    public Boolean getTaxesIncluded() {
        return taxesIncluded;
    }

    public void setTaxesIncluded(Boolean taxesIncluded) {
        this.taxesIncluded = taxesIncluded;
    }

    @JsonAttribute(name = "county_taxes")
    public Boolean getCountryTaxes() {
        return countryTaxes;
    }

    public void setCountryTaxes(Boolean countryTaxes) {
        this.countryTaxes = countryTaxes;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @JsonAttribute(name = "iana_timezone")
    public String getIanaTimezone() {
        return ianaTimezone;
    }

    public void setIanaTimezone(String ianaTimezone) {
        this.ianaTimezone = ianaTimezone;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @JsonAttribute(name = "has_storefront")
    public Boolean getHasStorefront() {
        return hasStorefront;
    }

    public void setHasStorefront(Boolean hasStorefront) {
        this.hasStorefront = hasStorefront;
    }

    @JsonAttribute(name = "setup_required")
    public Boolean getSetupRequired() {
        return setupRequired;
    }

    public void setSetupRequired(Boolean setupRequired) {
        this.setupRequired = setupRequired;
    }

    @JsonAttribute(name = "checkout_api_supported")
    public Boolean getCheckoutApiSupported() {
        return checkoutApiSupported;
    }

    public void setCheckoutApiSupported(Boolean checkoutApiSupported) {
        this.checkoutApiSupported = checkoutApiSupported;
    }

    @JsonAttribute(name = "multi_location_enabled")
    public Boolean getMultiLocationEnabled() {
        return multiLocationEnabled;
    }

    public void setMultiLocationEnabled(Boolean multiLocationEnabled) {
        this.multiLocationEnabled = multiLocationEnabled;
    }
}
