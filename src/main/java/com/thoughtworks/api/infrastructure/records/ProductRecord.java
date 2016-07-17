package com.thoughtworks.api.infrastructure.records;

import com.thoughtworks.api.infrastructure.core.Product;

/**
 * Created by syzhang on 7/17/16.
 */
public class ProductRecord implements Product{
    private String id;
    private String name;
    private String description;
    private float price;

    public ProductRecord(){
        id = "1";
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
}
