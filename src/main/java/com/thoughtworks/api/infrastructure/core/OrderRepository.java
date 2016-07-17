package com.thoughtworks.api.infrastructure.core;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by syzhang on 7/17/16.
 */
public interface OrderRepository {
    Order createOrder(Map<String, Object> info, String userId);
    float getPrice(String productId);
    List<Order> getOrdersForUser(String userId);
    Optional<Order> getOrderDetails(String orderId);
}
