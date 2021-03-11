package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

@CompiledJson
public class OAuthToken {
    private String accessToken;
    private String scope;
    private long expiresIn;
    private String associatedUserScope;
    private User associatedUser;

    @JsonAttribute(name = "access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @JsonAttribute(name = "expires_in")
    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    @JsonAttribute(name = "associated_user_scope")
    public String getAssociatedUserScope() {
        return associatedUserScope;
    }

    public void setAssociatedUserScope(String associatedUserScope) {
        this.associatedUserScope = associatedUserScope;
    }

    @JsonAttribute(name = "associated_user")
    public User getAssociatedUser() {
        return associatedUser;
    }

    public void setAssociatedUser(User associatedUser) {
        this.associatedUser = associatedUser;
    }
}
