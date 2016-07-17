package com.thoughtworks.api.infrastructure.core;

import java.util.Map;

/**
 * Created by syzhang on 7/17/16.
 */
public interface ProductRepository {

    Product createProduct(Map info);
}
