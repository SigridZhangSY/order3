package com.thoughtworks.api.web;

import com.thoughtworks.api.infrastructure.core.User;
import com.thoughtworks.api.infrastructure.core.UserRepository;
import com.thoughtworks.api.infrastructure.records.UserRecord;
import com.thoughtworks.api.web.exception.InvalidParameterException;
import com.thoughtworks.api.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

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
        if(userRepository.findUserByName(String.valueOf(info.get("name"))).isPresent())
            return Response.status(Response.Status.BAD_REQUEST).entity("User with same name already exists").build();
        User user = userRepository.createUser(info);
        return Response.created(routes.user(user)).build();
    }
}
