package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.core.Product;
import com.thoughtworks.api.infrastructure.mybatis.mappers.ProductMapper;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

/**
 * Created by syzhang on 7/17/16.
 */
public class ProductRepository implements com.thoughtworks.api.infrastructure.core.ProductRepository{

    @Inject
    ProductMapper productMapper;

    @Override
    public Product createProduct(Map info) {
        String productId = nextIdentity();
        info.put("productId", productId);
        productMapper.save(info);
        return productMapper.findById(productId);
    }

    private String nextIdentity() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
