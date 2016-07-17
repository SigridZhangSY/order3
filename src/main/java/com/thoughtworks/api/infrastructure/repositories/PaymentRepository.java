package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.core.Payment;
import com.thoughtworks.api.infrastructure.mybatis.mappers.PaymentMapper;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by syzhang on 7/17/16.
 */
public class PaymentRepository implements com.thoughtworks.api.infrastructure.core.PaymentRepository {
    @Inject
    PaymentMapper paymentMapper;

    @Override
    public Payment createPaymentForOrder(Map<String, Object> info,String userId, String orderId) {
        info.put("userId", userId);
        info.put("orderId", orderId);
        paymentMapper.savePayment(info);
        return paymentMapper.findPaymentById(orderId);
    }
}
