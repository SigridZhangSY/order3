package com.thoughtworks.api.web.jersey;

import com.thoughtworks.api.infrastructure.core.Order;
import com.thoughtworks.api.infrastructure.core.Payment;
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

    public URI order(Order order){return URI.create(baseUri + "users/" + order.getUserId() + "/orders/" + order.getId());}

    public URI payment(Payment payment){return URI.create(baseUri + "users/" + payment.getUserId() + "/orders/" + payment.getOrderId() + "/payment");}

    public URI orderById(String userId, String orderId){return URI.create(baseUri + "users/" + userId + "/orders/" + orderId + "/payment");}

}
