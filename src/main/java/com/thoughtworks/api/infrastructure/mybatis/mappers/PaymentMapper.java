package com.thoughtworks.api.infrastructure.mybatis.mappers;

import com.thoughtworks.api.infrastructure.core.Payment;

import java.util.Map;

/**
 * Created by syzhang on 7/17/16.
 */
public interface PaymentMapper {
    int savePayment(Map<String, Object> info);
    Payment findPaymentById(String orderId);
}
