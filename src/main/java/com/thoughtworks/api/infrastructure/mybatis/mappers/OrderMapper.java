package com.thoughtworks.api.infrastructure.mybatis.mappers;

import com.thoughtworks.api.infrastructure.core.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by syzhang on 7/17/16.
 */
public interface OrderMapper {
    int saveOrder(Map<String, Object> info);
    int saveOrderItem(List<Map<String, Object>> info);
    Order findById(String orderId);
    List<Order> findOrders(String userId);
    Order getOrderDetailsById(String orderId);
}
