package com.thoughtworks.api.infrastructure.records;

import com.thoughtworks.api.infrastructure.core.Product;
import com.thoughtworks.api.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by syzhang on 7/17/16.
 */
public class ProductRecord implements Product, Record{
    private String id;
    private String name;
    private String description;
    private float price;

    public ProductRecord(){

    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("uri", routes.product(ProductRecord.this));
        map.put("name", name);
        map.put("description", description);
        map.put("price", price);

        return map;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }


}
