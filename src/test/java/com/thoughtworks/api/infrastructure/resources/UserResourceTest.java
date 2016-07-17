package com.thoughtworks.api.infrastructure.resources;

import com.thoughtworks.api.infrastructure.core.Product;
import com.thoughtworks.api.infrastructure.core.ProductRepository;
import com.thoughtworks.api.infrastructure.core.User;
import com.thoughtworks.api.infrastructure.core.UserRepository;
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
import java.util.Map;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
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
    public void should_return_201_when_create_order(){
        User user = userRepository.createUser(TestHelper.user("kayla"));
        Product product = productRepository.createProduct(TestHelper.product("apple"));

        Response post = post("/users/" + user.getId() + "/products", TestHelper.order("kayla", product.getId()));
        assertThat(post.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
    }
}
