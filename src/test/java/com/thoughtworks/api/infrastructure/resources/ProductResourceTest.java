package com.thoughtworks.api.infrastructure.resources;

import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
public class ProductResourceTest extends ApiSupport {
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void should_able_return_uri_when_create_product_with_specified_parameter(){
        Response post = post("/products", TestHelper.product("apple"));
        assertThat(post.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
    }

    @Test
    public void should_able_return_400_when_name_is_null(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("description", "red apple");
        map.put("price", 1.2);

        Response post = post("/products", map);
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }

    @Test
    public void should_return_200_when_get_all_products(){
        Response get = get("/products");
        assertThat(get.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
    }

}