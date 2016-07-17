package com.thoughtworks.api.infrastructure.records;

import com.thoughtworks.api.infrastructure.core.OrderItem;

/**
 * Created by syzhang on 7/17/16.
 */
public class OrderItemRecord implements OrderItem{
    private String id;
    private String productId;
    private int quantity;
    private float amount;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getProductId() {
        return productId;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public float getAmount() {
        return amount;
    }
}
