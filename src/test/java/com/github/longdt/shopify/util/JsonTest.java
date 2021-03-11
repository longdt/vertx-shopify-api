package com.github.longdt.shopify.util;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonTest {

    @Test
    public void encode() {
        Buffer buffer = Buffer.buffer();
        String stringJson = "xin chao\nhello\t\\muc 1\"muc 2\"";
        System.out.println(stringJson);
        Json.encode(stringJson, buffer);
        System.out.println(buffer.toString());
    }

    public static void main(String[] args) {
        Buffer buffer = Buffer.buffer("{\"order\":{\"email\":\"foo@example.com\",\"fulfillment_status\":\"fulfilled\",\"line_items\":[{\"variant_id\":447654529,\"quantity\":1}]}}");
        System.out.println(buffer.toString());
        int lenth = buffer.length() - 2;
        JsonObject options = new JsonObject().put("send_receipt", true).put("send_fulfillment_receipt", true);
        Json.encode(options.getMap(), buffer);
        buffer.setString(lenth, ",  ");
        buffer.appendByte((byte) '}');
        System.out.println(buffer);
    }
}