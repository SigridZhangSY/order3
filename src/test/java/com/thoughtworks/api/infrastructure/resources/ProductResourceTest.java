package com.thoughtworks.api.infrastructure.resources;

import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;

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
    public void should_able_return_201_when_create_product(){
        Response post = post("/products", TestHelper.product("apple"));
        assertThat(post.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
    }

}