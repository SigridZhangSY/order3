package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.core.*;
import com.thoughtworks.api.support.DatabaseTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by syzhang on 7/17/16.
 */

@RunWith(DatabaseTestRunner.class)
public class PaymentRepositoryTest {
    @Inject
    OrderRepository orderRepository;
    @Inject
    ProductRepository productRepository;
    @Inject
    UserRepository userRepository;
    @Inject
    PaymentRepository paymentRepository;

    @Test
    public void should_save_and_get_payment(){
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        Order order = orderRepository.createOrder(TestHelper.order("kayla", product.getId()), user.getId());
        Payment payment = paymentRepository.createPaymentForOrder(TestHelper.payment(), user.getId(), order.getId());

        assertThat(payment.getOrderId(), is(order.getId()));
    }
}
