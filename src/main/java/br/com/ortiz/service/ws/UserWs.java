package br.com.ortiz.service.ws;

import br.com.ortiz.service.ejb.UserService;
import br.com.ortiz.domain.entity.User;
import br.com.ortiz.domain.entity.UserToken;
import br.com.ortiz.service.ws.util.ResponseUtil;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

/**
 * Created by marcelo on 09/02/17.
 */
@Path("/users")
public class UserWs {

    @Inject
    private UserService userService;

    @Path("/")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> findAll() {
        return userService.findAll();
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findById(@PathParam("id") Long id) {
        Optional<User> topicOptional = userService.findById(id);
        return topicOptional.map((User user) -> ResponseUtil.ok(user)).orElse(ResponseUtil.notFound());
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public UserToken save(User user) throws UnsupportedEncodingException {
        return userService.saveAndCreateToken(user);
    }

}
