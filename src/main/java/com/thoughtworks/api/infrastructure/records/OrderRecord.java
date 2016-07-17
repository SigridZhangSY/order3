package com.thoughtworks.api.infrastructure.records;

import com.thoughtworks.api.infrastructure.core.Order;
import com.thoughtworks.api.web.jersey.Routes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by syzhang on 7/17/16.
 */
public class OrderRecord implements Order, Record {

    private String id;
    private String userId;
    private String name;
    private String address;
    private String phone;
    private float totalPrice;
    private String time;
    private List<OrderItemRecord> items;


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

    @Override
    public Map<String, Object> toJson(Routes routes) {
        Map<String, Object> map = new HashMap<>();
        map.put("uri", routes.order(OrderRecord.this));
        map.put("name", name);
        map.put("address", address);
        map.put("phone", phone);
        map.put("created_at", time);

        return map;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }


}
