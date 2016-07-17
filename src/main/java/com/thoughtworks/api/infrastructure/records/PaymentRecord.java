package com.thoughtworks.api.infrastructure.records;

import com.thoughtworks.api.infrastructure.core.Payment;
import com.thoughtworks.api.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by syzhang on 7/17/16.
 */
public class PaymentRecord implements Payment, Record {
    private String orderId;
    private String payType;
    private float amount;
    private String userId;
    private String time;

    public PaymentRecord(String userId, String orderId){
        this.orderId = orderId;
        this.userId = userId;
    }
    public PaymentRecord(){
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

    @Override
    public Map<String, Object> toJson(Routes routes) {
        Map<String, Object> map = new HashMap<>();
        map.put("order_uri", routes.orderById(userId, orderId));
        map.put("uri", routes.payment(PaymentRecord.this));
        map.put("payment_type", payType);
        map.put("amount", amount);

        return map;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }
}
