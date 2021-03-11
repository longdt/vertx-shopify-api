package com.github.longdt.shopify.model.internal;

import com.dslplatform.json.CompiledJson;
import com.github.longdt.shopify.model.Webhook;

@CompiledJson
public class WebhookWrapper {
    private Webhook webhook;

    public WebhookWrapper(Webhook webhook) {
        this.webhook = webhook;
    }

    public Webhook getWebhook() {
        return webhook;
    }
}
