package com.thoughtworks.api.web.jersey;

import com.thoughtworks.api.infrastructure.core.Order;
import com.thoughtworks.api.infrastructure.core.Product;
import com.thoughtworks.api.infrastructure.core.User;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

//    public Routes(UriInfo uriInfo) {
//        baseUri = uriInfo.getBaseUri().toASCIIString();
//    }

    public Routes(UriInfo uriInfo) {
        baseUri = "/";
    }


    public URI product(Product product){return URI.create(baseUri + "products/" + product.getId());}

    public URI user(User user){return URI.create(baseUri + "users/" + user.getId());}

    public URI order(Order order){return URI.create(baseUri + "users/" + order.getUserId() + "/products/" + order.getId());}
}
