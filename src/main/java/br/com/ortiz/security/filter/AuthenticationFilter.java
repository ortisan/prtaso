package br.com.ortiz.security.filter;

import br.com.ortiz.domain.entity.User;
import br.com.ortiz.security.annotation.Secured;
import br.com.ortiz.service.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.util.Optional;

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
            Optional<User> userFromToken = JwtUtil.getUserFromToken(token);
            // TODO PERMISSIONS
        } catch (Exception exc) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
    }
}
