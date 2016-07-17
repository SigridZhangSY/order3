package com.thoughtworks.api.web.jersey;

import com.thoughtworks.api.domain.user.User;
import com.thoughtworks.api.infrastructure.core.Product;

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

    public URI userUrl(User user) {
        return URI.create(String.format("%susers/%s", baseUri, user.getUserId().id()));
    }

    public URI product(Product product){return URI.create(baseUri + "products/" + product.getId());}
}
