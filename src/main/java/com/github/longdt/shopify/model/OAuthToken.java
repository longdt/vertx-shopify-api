package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
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

    @JsonAttribute(name = "expires_in")
    public long getExpiresIn() {
        return expiresIn;
    }

    @JsonAttribute(name = "associated_user_scope")
    public String getAssociatedUserScope() {
        return associatedUserScope;
    }

    @JsonAttribute(name = "associated_user")
    public User getAssociatedUser() {
        return associatedUser;
    }
}
