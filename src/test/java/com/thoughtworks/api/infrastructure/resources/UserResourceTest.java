package com.thoughtworks.api.infrastructure.resources;

import com.thoughtworks.api.infrastructure.core.*;
import com.thoughtworks.api.infrastructure.core.PaymentRepository;
import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by syzhang on 7/17/16.
 */

@RunWith(ApiTestRunner.class)
public class UserResourceTest extends ApiSupport {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Inject
    OrderRepository orderRepository;

    @Inject
    PaymentRepository paymentRepository;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void should_return_210_when_create_user_with_specified_parameter(){
        Response post = post("/users", TestHelper.user("kayla"));
        assertThat(post.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
    }

    @Test
    public void should_return_400_when_user_exists(){
        userRepository.createUser(TestHelper.user("kayla"));
        Response post = post("/users", TestHelper.user("kayla"));
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));

    }

    @Test
    public void should_return_400_when_name_is_null(){
        Map<String, Object> map = new HashMap<>();
        Response post = post("/users", map);
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));

    }

    @Test
    public void should_return_201_when_create_order_with_specified_parameter(){
        User user = userRepository.createUser(TestHelper.user("kayla"));
        Product product = productRepository.createProduct(TestHelper.product("apple"));

        Response post = post("/users/" + user.getId() + "/orders", TestHelper.order("kayla", product.getId()));
        assertThat(post.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));

    }

    @Test
    public void should_return_400_when_create_order_with_user_not_exists(){
        User user = userRepository.createUser(TestHelper.user("kayla"));
        Product product = productRepository.createProduct(TestHelper.product("apple"));

        Response post = post("/users/1/orders", TestHelper.order("kayla", product.getId()));
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));

    }

    @Test
    public void should_return_400_when_create_order_with_name_is_null(){
        User user = userRepository.createUser(TestHelper.user("kayla"));
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        Map<String, Object> map = new HashMap<>();
        Response post = post("/users/1/orders", map);
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));

    }

    @Test
    public void should_return_400_when_create_order_with_product_not_exists(){
        User user = userRepository.createUser(TestHelper.user("kayla"));
        Response post = post("/users/" + user.getId() + "/orders", TestHelper.order("kayla", "1"));
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));

    }

    @Test
    public void should_return_detail_when_get_orders(){
        User user = userRepository.createUser(TestHelper.user("kayla"));
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        Order order = orderRepository.createOrder(TestHelper.order("kayla",product.getId()), user.getId());
        Response get = get("/users/" + user.getId() + "/orders");
        assertThat(get.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
        final List<Map<String, Object>> res = get.readEntity(List.class);
        assertEquals(1, res.size());
        assertThat(res.get(0).get("uri"), is("/users/" + user.getId() + "/orders/" + order.getId()));
        assertThat(get.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
    }

    @Test
    public void should_return_400_when_get_orders_with_product_not_exists(){
        User user = userRepository.createUser(TestHelper.user("kayla"));
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        Order order = orderRepository.createOrder(TestHelper.order("kayla",product.getId()), user.getId());
        Response get = get("/users/1/orders");
        assertThat(get.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));

    }

    @Test
    public void should_return_detail_when_get_a_order(){
        User user = userRepository.createUser(TestHelper.user("kayla"));
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        Order order = orderRepository.createOrder(TestHelper.order("kayla",product.getId()), user.getId());
        Response get = get("/users/" + user.getId() + "/orders/" + order.getId());
        assertThat(get.getStatus(), is(HttpStatus.OK_200.getStatusCode()));


        final Map<String, Object> res = get.readEntity(Map.class);
        assertThat(res.get("uri"), is("/users/" + user.getId() + "/orders/" + order.getId()));

    }

    @Test
    public void should_return_400_when_order_no_exist(){
        User user = userRepository.createUser(TestHelper.user("kayla"));
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        Order order = orderRepository.createOrder(TestHelper.order("kayla",product.getId()), user.getId());
        Response get = get("/users/" + user.getId() + "/orders/1");
        assertThat(get.getStatus(), is(HttpStatus.NOT_FOUND_404.getStatusCode()));
    }

    @Test
    public void should_return_201_when_create_payment_with_specified_parameter(){
        User user = userRepository.createUser(TestHelper.user("kayla"));
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        Order order = orderRepository.createOrder(TestHelper.order("kayla",product.getId()), user.getId());

        Response post = post("/users/" + user.getId() + "/orders/" + order.getId() + "/payment", TestHelper.payment());
        assertThat(post.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));

    }

    @Test
    public void should_return_400_when_payment_exists(){
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        Order order = orderRepository.createOrder(TestHelper.order("kayla", product.getId()), user.getId());
        Payment payment = paymentRepository.createPaymentForOrder(TestHelper.payment(),user.getId(), order.getId());

        Response post = post("/users/" + user.getId() + "/orders/" + order.getId() + "/payment", TestHelper.payment());
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }

    @Test
    public void should_return_400_when_payType_is_null(){
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        String userId = user.getId();
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        String productId = product.getId();
        Order order = orderRepository.createOrder(TestHelper.order("kayla", productId), userId);
        String orderId = order.getId();
        Map payMap = new HashMap<String, Object>();
        payMap.put("amount", "100");

        Response post = post("/users/" + userId + "/orders/" + orderId + "/payment", payMap);
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }

    @Test
    public void should_return_detail_when_get_payment(){
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        Order order = orderRepository.createOrder(TestHelper.order("kayla", product.getId()), user.getId());
        Payment payment = paymentRepository.createPaymentForOrder(TestHelper.payment(), user.getId(), order.getId());

        Response get = get("/users/" + user.getId() + "/orders/" + order.getId() + "/payment");
        assertThat(get.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
        final Map<String, Object> res = get.readEntity(Map.class);
        assertThat(res.get("uri"), is("/users/" + user.getId() + "/orders/" + order.getId() + "/payment"));

    }

}
