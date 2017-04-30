package br.com.ortiz.service.ejb;

import br.com.ortiz.domain.dao.TwitterRequestTokenDao;
import br.com.ortiz.domain.dao.UserDao;
import br.com.ortiz.domain.entity.TwitterRequestToken;
import br.com.ortiz.domain.entity.User;
import br.com.ortiz.domain.entity.UserToken;
import br.com.ortiz.service.util.JwtUtil;
import br.com.ortiz.to.LoginTo;
import br.com.ortiz.to.SignResultTo;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by marcelo on 09/02/17.
 */
@Stateless
public class UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private TwitterRequestTokenDao twitterRequestTokenDao;

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

    public Optional<User> findByToken(String token) {
        return JwtUtil.getUserIdFromToken(token).map((Long userId) -> userDao.find(userId)).orElse(Optional.empty());
    }

    public TwitterRequestToken saveTwitterRequestRequestToken(RequestToken requestToken) {
        Optional<TwitterRequestToken> requestTokenOptional = twitterRequestTokenDao.findByRequestToken(requestToken.getToken());
        if (requestTokenOptional.isPresent())
            return requestTokenOptional.get();
        else {
            TwitterRequestToken twitterRequestToken = new TwitterRequestToken();
            twitterRequestToken.setRequestToken(requestToken.getToken());
            twitterRequestToken.setTokenSecret(requestToken.getTokenSecret());
            twitterRequestTokenDao.save(twitterRequestToken);
            return twitterRequestToken;
        }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public UserToken saveAndCreateToken(User user) {
        save(user);
        String token = JwtUtil.getTokenFromUser(user);
        UserToken userToken = new UserToken();
        userToken.setId(user.getId());
        userToken.setName(user.getName());
        userToken.setUsername(user.getUsername());
        userToken.setToken(token);
        return userToken;
    }

    public Optional<SignResultTo> signin(LoginTo loginTo) {
        return findByUsernameAndPassword(loginTo.getUsername(), loginTo.getPassword()).map((User u) -> {
            String token = JwtUtil.getTokenFromUser(u);
            SignResultTo signResult = new SignResultTo();
            signResult.setUserId(u.getId());
            signResult.setName(u.getName());
            signResult.setUsername(u.getUsername());
            signResult.setToken(token);
            return Optional.of(signResult);
        }).orElse(Optional.empty());
    }

    public Optional<SignResultTo> signin(String token) {
        return findByToken(token).map((User u) -> {
            final SignResultTo signResult = new SignResultTo();
            signResult.setUserId(u.getId());
            signResult.setName(u.getName());
            signResult.setUsername(u.getUsername());
            signResult.setToken(token);
            return Optional.of(signResult);
        }).orElse(Optional.empty());
    }


    public SignResultTo saveAndSignin(AccessToken accessToken) {
        Long twitterUserId = accessToken.getUserId();
        Optional<User> userOpt = findByUserByTwitterUserId(twitterUserId);
        User user = null;
        if (userOpt.isPresent()) {
            user = userOpt.get();
        } else {
            user = new User();
            user.setName(accessToken.getScreenName());
            user.setTwitterUserId("" + twitterUserId);
            user = userDao.save(user);
        }

        String token = JwtUtil.getTokenFromUser(user);
        SignResultTo signResult = new SignResultTo();
        signResult.setUserId(user.getId());
        signResult.setName(user.getName());
        signResult.setUsername(user.getUsername());
        signResult.setToken(token);

        return signResult;
    }

    public Optional<TwitterRequestToken> getTwitterRequestTokenByToken(String requestToken) {
        return twitterRequestTokenDao.findByRequestToken(requestToken);
    }

    public Optional<User> findByUserByTwitterUserId(Long twitterUserId) {
        return userDao.findByTwitterUserId(twitterUserId);
    }

}
