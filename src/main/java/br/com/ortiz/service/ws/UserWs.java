package br.com.ortiz.service.ws;

import br.com.ortiz.business.ejb.UserService;
import br.com.ortiz.domain.entity.User;
import br.com.ortiz.domain.entity.UserToken;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
    public User findById(@PathParam("id") Long id) {
        return userService.findById(id);
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public UserToken save(User user) throws UnsupportedEncodingException {
        userService.save(user);
        String token = userService.createToken(user);
        UserToken userToken = new UserToken();
        userToken.setId(user.getId());
        userToken.setName(user.getName());
        userToken.setUsername(user.getUsername());
        userToken.setToken(token);
        return userToken;
    }

}
