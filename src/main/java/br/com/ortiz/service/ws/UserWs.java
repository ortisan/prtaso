package br.com.ortiz.service.ws;

import br.com.ortiz.business.ejb.UserService;
import br.com.ortiz.domain.entity.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by marcelo on 09/02/17.
 */
@Path("/user")
public class UserWs {

    @Inject
    private UserService userService;

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public User findById(@PathParam("id") Long id) {
        return userService.findById(id);
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public User save(User user) {
        userService.save(user);
        return user;
    }

}
