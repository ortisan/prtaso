package br.com.ortiz.service.ws.util;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**s
 * Created by tenta on 11/03/2017.
 */
public class ResponseUtil {

    public static Response ok(Object... obj) {
        return javax.ws.rs.core.Response.ok(obj, MediaType.APPLICATION_JSON).build();
    }

    public static Response unautorized() {
        return javax.ws.rs.core.Response.status(UNAUTHORIZED).build();
    }

    public static Response notFound() {
        return javax.ws.rs.core.Response.status(NOT_FOUND).build();
    }


}
