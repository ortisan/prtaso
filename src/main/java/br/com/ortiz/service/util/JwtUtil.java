package br.com.ortiz.service.util;

import br.com.ortiz.domain.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

/**
 * Created by tenta on 29/04/2017.
 */
public class JwtUtil {

    private static final String SECRET = "aloha";
    private static final String USERNAME = "username";
    private static final String USER_ID = "USER_ID";
    private static final String TWITTER_USER_ID = "twitter_user_id";

    public static Optional<User> getUserFromToken(String token) {
        User user = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            Integer userId = decodedJWT.getClaim(USER_ID).asInt();
            String username = decodedJWT.getClaim(USERNAME).asString();
            String twitterUserId = decodedJWT.getClaim(TWITTER_USER_ID).asString();
            user = new User();
            user.setId(userId.longValue());
            user.setUsername(username);
            user.setTwitterUserId(twitterUserId);
        } catch (Exception exc) {
            // Invalid token.
        }
        return Optional.ofNullable(user);
    }

    public static Optional<Long> getUserIdFromToken(String token) {
        return getUserFromToken(token).map((User user) -> user.getId());
    }

    public static String getTokenFromUser(User user) {
        try {
            return JWT.create().withClaim(USER_ID, user.getId().intValue())
            .withClaim(USERNAME, user.getUsername())
            .withClaim(TWITTER_USER_ID, user.getTwitterUserId())
            .sign(Algorithm.HMAC256(SECRET));
        } catch (UnsupportedEncodingException e) {
            // Never will throw this exception.
            return "";
        }
    }


}
