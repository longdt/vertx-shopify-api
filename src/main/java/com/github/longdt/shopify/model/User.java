package com.github.longdt.shopify.model;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@CompiledJson
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean accountOwner;

    @JsonAttribute(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonAttribute(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonAttribute(name = "account_owner")
    public boolean isAccountOwner() {
        return accountOwner;
    }
}
