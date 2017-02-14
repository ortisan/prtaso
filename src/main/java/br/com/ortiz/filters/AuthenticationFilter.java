package br.com.ortiz.filters;

import br.com.ortiz.annotations.Secured;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

/**
 * Created by marcelo on 09/02/17.
 */
@Secured
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
    public void filter(ContainerRequestContext requestContext) {
        String autorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String token = StringUtils.substringAfter(autorizationHeader, "Bearer ");
        if (StringUtils.isEmpty(token)) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("aloha")).build();
            DecodedJWT verify = verifier.verify(token);
            requestContext.setProperty("username", verify.getClaim("username"));
            requestContext.setProperty("user_id", verify.getClaim("user_id"));

        } catch (Exception exc) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
    }
}
