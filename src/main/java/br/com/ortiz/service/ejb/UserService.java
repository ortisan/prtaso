package br.com.ortiz.service.ejb;

import br.com.ortiz.domain.dao.UserDao;
import br.com.ortiz.domain.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

/**
 * Created by marcelo on 09/02/17.
 */
@Stateless
public class UserService {

    @Inject
    private UserDao userDao;

    public User save(User user) {
        return userDao.save(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public Optional<User> findById(Long id) {
        return userDao.find(id);
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        Optional<User> user = userDao.findByUsernameAndPassword(username, password);
        return user;
    }

    public String createToken(User user) {
        try {
            return JWT.create().withClaim("user_id", user.getId().toString()).withClaim("username", user.getUsername()).sign(Algorithm.HMAC256("aloha"));
        } catch (UnsupportedEncodingException e) {
            // Never will throw this exception.
            return "";
        }
    }

    public void signinTwitter(String twitterToken, String urlCallback) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        Optional<User> userOptional = userDao.findByTwitterToken(twitterToken);
        RequestToken requestToken = null;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            requestToken = new RequestToken(user.getTwitterToken(), user.getTwitterTokenSecret());
            try {
                AccessToken oAuthAccessToken = twitter.getOAuthAccessToken(requestToken, user.getTwitterVerifier());
            } catch (Exception exc) {
                requestToken = twitter.getOAuthRequestToken(urlCallback);
            }
        } else {
            requestToken = twitter.getOAuthRequestToken(urlCallback);
        }
    }
}
