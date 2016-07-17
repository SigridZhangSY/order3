package com.thoughtworks.api.infrastructure.core;

/**
 * Created by syzhang on 7/17/16.
 */
public interface Payment {
    String getOrderId();
    String getUserId();
    String getPayType();
    float getAmount();
    String getTime();
}
