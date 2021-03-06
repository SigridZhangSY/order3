package com.thoughtworks.api.web;

import com.thoughtworks.api.infrastructure.core.Product;
import com.thoughtworks.api.infrastructure.core.ProductRepository;
import com.thoughtworks.api.infrastructure.records.ProductRecord;
import com.thoughtworks.api.web.exception.InvalidParameterException;
import com.thoughtworks.api.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * Created by syzhang on 7/17/16.
 */

@Path("products")
public class ProductResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Map<String, Object> info,
                                  @Context Routes routes,
                                  @Context ProductRepository productRepository){
        if(info.getOrDefault("name", "").toString().trim().isEmpty() ||
                info.getOrDefault("description", "").toString().trim().isEmpty() ||
                info.getOrDefault("price", "").toString().trim().isEmpty())
            throw new InvalidParameterException("name, description and price are required");

        Product product= productRepository.createProduct(info);
        return Response.created(routes.product(product)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProducts(@Context ProductRepository productRepository){
        return productRepository.getAllProducts();
    }

    @GET
    @Path("{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProductById(@PathParam("productId") String productId,
                                 @Context ProductRepository productRepository){
        return productRepository.findProductById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
    }

}


