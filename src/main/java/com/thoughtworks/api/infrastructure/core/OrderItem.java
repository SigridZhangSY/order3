package com.thoughtworks.api.infrastructure.core;

/**
 * Created by syzhang on 7/17/16.
 */
public interface OrderItem {
    String getId();
    String getProductId();
    int getQuantity();
    float getAmount();
}
