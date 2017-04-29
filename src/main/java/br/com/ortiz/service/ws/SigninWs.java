package br.com.ortiz.service.ws;

import br.com.ortiz.service.ejb.UserService;
import br.com.ortiz.service.ws.util.ResponseUtil;
import br.com.ortiz.to.LoginTo;
import br.com.ortiz.to.SignResultTo;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;

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
        return userService.signin(loginTo).map((SignResultTo s) -> ResponseUtil.ok(s)).orElse(ResponseUtil.unauthorized());
    }

    @Path("/{token}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response loginWithToken(@PathParam("token") String token) throws UnsupportedEncodingException {
        return userService.signin(token).map((SignResultTo s) -> ResponseUtil.ok(s)).orElse(ResponseUtil.unauthorized());
    }
}
