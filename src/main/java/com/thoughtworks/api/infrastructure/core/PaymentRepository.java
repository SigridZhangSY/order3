package com.thoughtworks.api.infrastructure.core;

import java.util.Map;
import java.util.Optional;

/**
 * Created by syzhang on 7/17/16.
 */
public interface PaymentRepository {
    Payment createPaymentForOrder(Map<String, Object> info, String userId, String orderId);
    Optional<Payment> findPaymentByOrderId(String orderId);
}
