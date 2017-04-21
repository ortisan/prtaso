package br.com.ortiz.service.ws;

import br.com.ortiz.service.ejb.UserService;
import br.com.ortiz.domain.entity.User;
import br.com.ortiz.service.ws.util.ResponseUtil;
import br.com.ortiz.to.LoginTo;
import br.com.ortiz.to.SignResultTo;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

/**
 * Created by marcelo on 09/02/17.
 */
@Path("/signin")
public class SigninWs {

    @Inject
    private UserService userService;

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(LoginTo loginTo) throws UnsupportedEncodingException {

        Optional<User> userOptional = userService.findByUsernameAndPassword(loginTo.getUsername(), loginTo.getPassword());

        return userOptional.map((User u) -> {
            String token = userService.createToken(u);
            SignResultTo signResult = new SignResultTo();
            signResult.setUserId(u.getId());
            signResult.setToken(token);
            return ResponseUtil.ok(signResult);
        }).orElse(ResponseUtil.unauthorized());
    }
}
