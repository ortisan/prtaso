package br.com.ortiz.service.ws;

import br.com.ortiz.domain.entity.TwitterRequestToken;
import br.com.ortiz.service.ejb.UserService;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

/**
 * Created by tenta on 23/03/2017.
 */
@Path("/twitter")
public class TwitterWs {
    // TODO CHANGE TO PRODUCTION
    private static final String CALLBACK_BACKEND_URL = "http://localhost:8080/prtaso/api/twitter/callback";
    private static final String CALLBACK_CLIENT_URL = "http://localhost:3000/twitter/callback/%s";
    private static RequestToken requestToken;

    @Inject
    private UserService userService;

    @Path("/signin{twitterAccessToken : ([a-zA-Z0-9]*)}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signIn(@PathParam("twitterAccessToken") String twitterAccessToken) throws TwitterException, MalformedURLException, URISyntaxException {
        Twitter twitter = new TwitterFactory().getInstance();
        RequestToken requestToken = twitter.getOAuthRequestToken(CALLBACK_BACKEND_URL);

        userService.saveTwitterRequestRequestToken(requestToken);

        return Response.temporaryRedirect(new URL(requestToken.getAuthenticationURL()).toURI()).build();
    }

    @Path("/callback")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response callback(@QueryParam("oauth_token") String oauthToken, @QueryParam("oauth_verifier") String oauthVerifier) throws MalformedURLException, URISyntaxException {
        Optional<TwitterRequestToken> twitterRequestTokenByToken = userService.getTwitterRequestTokenByToken(oauthToken);
        TwitterRequestToken twitterRequestToken = twitterRequestTokenByToken.get();
        RequestToken requestToken = new RequestToken(twitterRequestToken.getRequestToken(), twitterRequestToken.getTokenSecret());

        try {
            Twitter twitter = new TwitterFactory().getInstance();

            AccessToken oAuthAccessToken = twitter.getOAuthAccessToken(requestToken, oauthVerifier);

            // TODO VERIFY IF USER EXISTS, IF NOT, SAVE IT AND SHOW THE FORM FOR PERSONAL INFORMATIONS

            System.out.println("oAuthAccessToken.getScreenName() = " + oAuthAccessToken.getScreenName());
            System.out.println("oAuthAccessToken.getUserId() = " + oAuthAccessToken.getUserId());
            System.out.println("oAuthAccessToken.getToken() = " + oAuthAccessToken.getToken());
            System.out.println("oAuthAccessToken.getTokenSecret() = " + oAuthAccessToken.getTokenSecret());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.temporaryRedirect(new URL(String.format(CALLBACK_CLIENT_URL, "123456")).toURI()).build();
    }
}
