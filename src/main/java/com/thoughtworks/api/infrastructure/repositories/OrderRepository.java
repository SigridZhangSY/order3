package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.core.Order;
import com.thoughtworks.api.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.api.infrastructure.mybatis.mappers.ProductMapper;

import javax.inject.Inject;
import java.util.*;

/**
 * Created by syzhang on 7/17/16.
 */
public class OrderRepository implements com.thoughtworks.api.infrastructure.core.OrderRepository {
    @Inject
    ProductMapper productMapper;
    @Inject
    OrderMapper orderMapper;

    @Override
    public Order createOrder(Map<String, Object> info, String userId) {
        float totalPrice = 0;
        String orderId = nextIdentity();
        List<Map<String, Object>> items = (List<Map<String, Object>>)info.get("order_items");
        for(int i = 0; i < items.size(); i++){
            items.get(i).put("order_id", orderId);
            float price = getPrice(String.valueOf(items.get(i).get("product_id")));
            totalPrice += price * Integer.valueOf(String.valueOf(items.get(i).get("quantity")));
            items.get(i).put("amount", price);
            items.get(i).put("id", nextIdentity());
        }


        System.out.println(info.get("order_items") + "hahaha");

//        List<Map<String, Object>> newItems = new ArrayList<Map<String, Object>>();


        info.put("id", orderId);
        info.put("user_id", userId);
        info.put("total_price", totalPrice);
        info.replace("order_items", items);

        orderMapper.saveOrder(info);
//        orderMapper.saveOrderItem(items);

        return orderMapper.findById(orderId);
    }

    @Override
    public float getPrice(String productId) {
        return productMapper.getPrice(productId);
    }

    @Override
    public List<Order> getOrdersForUser(String userId) {
        return orderMapper.findOrders(userId);
    }

    @Override
    public Optional<Order> getOrderDetails(String orderId) {
        return Optional.ofNullable(orderMapper.getOrderDetailsById(orderId));
    }

    private String nextIdentity() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
