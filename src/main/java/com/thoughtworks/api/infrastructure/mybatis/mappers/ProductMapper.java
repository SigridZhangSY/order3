package com.thoughtworks.api.infrastructure.mybatis.mappers;

import com.thoughtworks.api.infrastructure.core.Product;

import java.util.Map;

/**
 * Created by syzhang on 7/17/16.
 */
public interface ProductMapper {
    int save(Map info);

    Product findById(String id);
}
