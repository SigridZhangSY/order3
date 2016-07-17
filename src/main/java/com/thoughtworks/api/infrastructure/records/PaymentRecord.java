package com.thoughtworks.api.infrastructure.records;

import com.thoughtworks.api.infrastructure.core.Payment;

/**
 * Created by syzhang on 7/17/16.
 */
public class PaymentRecord implements Payment {
    private String orderId;
    private String payType;
    private float amount;
    private String userId;
    private String time;

    public PaymentRecord(String userId, String orderId){
        this.orderId = orderId;
        this.userId = userId;
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getPayType() {
        return payType;
    }

    @Override
    public float getAmount() {
        return amount;
    }

    @Override
    public String getTime() {
        return time;
    }
}
