package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.core.Product;
import com.thoughtworks.api.support.DatabaseTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by syzhang on 7/17/16.
 */
@RunWith(DatabaseTestRunner.class)
public class ProductRepositoryTest {
    @Inject
    ProductRepository productRepository;

    @Test
    public void should_create_and_get_product(){

        Product product = productRepository.createProduct(TestHelper.product("apple"));

        assertThat(product.getName(), is("apple"));
        assertThat(product.getDescription(), is("red apple"));
        assertEquals(product.getPrice(), 1.1, 0.01);

    }
    @Test
    public void should_get_all_products(){

        productRepository.createProduct(TestHelper.product("apple"));
        List<Product> res = productRepository.getAllProducts();

        assertEquals(res.size(), 1);
        assertThat(res.get(0).getName(), is("apple"));
    }

    @Test
    public void should_find_product_by_id(){
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        Product product_res = productRepository.findProductById(product.getId()).orElseThrow(() -> new NotFoundException("Product not found"));;

        assertThat(product_res.getName(), is("apple"));
    }


}
