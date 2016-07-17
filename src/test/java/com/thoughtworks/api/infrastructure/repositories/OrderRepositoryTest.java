package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.core.*;
import com.thoughtworks.api.support.DatabaseTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


/**
 * Created by syzhang on 7/17/16.
 */

@RunWith(DatabaseTestRunner.class)

public class OrderRepositoryTest {

    @Inject
    OrderRepository orderRepository;
    @Inject
    ProductRepository productRepository;
    @Inject
    UserRepository userRepository;

    @Test
    public void should_get_price(){
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        assertEquals(orderRepository.getPrice(product.getId()), 1.1, 0.01);
    }

    @Test
    public void should_save_and_find_order(){
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        String userId = user.getId();
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        String productId = product.getId();
        Order order = orderRepository.createOrder(TestHelper.order("kayla", productId), userId);

        assertThat(order.getName(), is("kayla"));
    }

    @Test
    public void should_find_order_for_user() {
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        String userId = user.getId();
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        String productId = product.getId();
        Order order = orderRepository.createOrder(TestHelper.order("kayla", productId), userId);

        List<Order> order_res = orderRepository.getOrdersForUser(userId);
        assertEquals(order_res.size(), 1);
        assertThat(order_res.get(0).getName(), is("kayla"));

    }

    @Test
    public void should_get_order_details(){
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        String userId = user.getId();
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        String productId = product.getId();
        Order order = orderRepository.createOrder(TestHelper.order("kayla", productId), userId);

        Order order_res = orderRepository.getOrderDetails(order.getId()).orElseThrow(() -> new NotFoundException("Order not found"));
        assertThat(order_res.getName(), is("kayla"));

    }

}
