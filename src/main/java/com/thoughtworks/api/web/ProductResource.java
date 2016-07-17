package com.thoughtworks.api.web;

import com.thoughtworks.api.infrastructure.core.Product;
import com.thoughtworks.api.infrastructure.records.ProductRecord;
import com.thoughtworks.api.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by syzhang on 7/17/16.
 */

@Path("products")
public class ProductResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(@Context Routes routes){
        Product product = new ProductRecord();
        System.out.println(product.getId());
        return Response.created(routes.product(product)).build();
    }
}


