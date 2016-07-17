package com.thoughtworks.api.infrastructure.core;

import com.thoughtworks.api.infrastructure.records.OrderItemRecord;

import java.util.List;

/**
 * Created by syzhang on 7/17/16.
 */
public interface Order {
    String getId();
    String getName();
    public String getUserId();
    String getAddress();
    String getPhone();
    float getTotalPrice();
    String getTime();
    public List<OrderItemRecord> getItems();
}
