package com.thoughtworks.api.infrastructure.records;

import com.thoughtworks.api.infrastructure.core.Order;

import java.util.List;

/**
 * Created by syzhang on 7/17/16.
 */
public class OrderRecord implements Order {

    private String id;
    private String userId;
    private String name;
    private String address;
    private String phone;
    private float totalPrice;
    private String time;
    private List<OrderItemRecord> items;

    public OrderRecord(String userId){
        id = "1";
        this.userId = userId;
    }

    public OrderRecord(){

    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public float getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public List<OrderItemRecord> getItems() {
        return items;
    }
}
