package com.thoughtworks.api.web;

import com.thoughtworks.api.infrastructure.core.*;
import com.thoughtworks.api.infrastructure.records.OrderRecord;
import com.thoughtworks.api.infrastructure.records.UserRecord;
import com.thoughtworks.api.web.exception.InvalidParameterException;
import com.thoughtworks.api.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by syzhang on 7/17/16.
 */

@Path("users")
public class UserResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Map<String, Object> info,
                                  @Context UserRepository userRepository,
                                  @Context Routes routes){
        if(info.getOrDefault("name", "").toString().trim().isEmpty())
            throw new InvalidParameterException("name is required");
        if(userRepository.findUserByName(String.valueOf(info.get("name"))).isPresent())
            return Response.status(Response.Status.BAD_REQUEST).entity("User with same name already exists").build();
        User user = userRepository.createUser(info);
        return Response.created(routes.user(user)).build();
    }

    @POST
    @Path("{userId}/orders")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(Map<String, Object> info,
                                @PathParam("userId") String userId,
                                @Context Routes routes,
                                @Context OrderRepository orderRepository,
                                @Context UserRepository userRepository,
                                @Context ProductRepository productRepository){
        if(info.getOrDefault("name", "").toString().trim().isEmpty() ||
                info.getOrDefault("address", "").toString().trim().isEmpty() ||
                info.getOrDefault("phone", "").toString().trim().isEmpty() ||
                info.getOrDefault("order_items", "").toString().trim().isEmpty())
            throw new InvalidParameterException("name, address, phone and order_items are required");
        List<Map<String, Object>> items = (List<Map<String, Object>>)info.get("order_items");
        if(items.size() == 0)
            throw new InvalidParameterException("order_items can't be empty");
        for(int i = 0; i < items.size(); i++) {
            if (items.get(i).getOrDefault("product_id", "").toString().trim().isEmpty() ||
                    items.get(i).getOrDefault("quantity", "").toString().trim().isEmpty())
                throw new InvalidParameterException("product_id and quantity are required");
            if(productRepository.findProductById(String.valueOf(items.get(i).get("product_id"))).isPresent() == false)
                throw new InvalidParameterException("product not exists");
        }
        Optional<User> user = userRepository.findUserById(userId);
        if(user.isPresent() == false)
            return Response.status(Response.Status.BAD_REQUEST).entity("User dose not exists").build();


        return Response.created(routes.order(orderRepository.createOrder(info, userId))).build();
    }

    @GET
    @Path("{userId}/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAllOrders(@PathParam("userId") String userId,
                               @Context OrderRepository orderRepository,
                                    @Context UserRepository userRepository){
        if(userRepository.findUserById(userId).isPresent() == false)
            throw new InvalidParameterException("user not exists");
        return orderRepository.getOrdersForUser(userId);
    }

}
