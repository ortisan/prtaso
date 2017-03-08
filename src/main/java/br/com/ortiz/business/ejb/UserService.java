package br.com.ortiz.business.ejb;

import br.com.ortiz.domain.dao.UserDao;
import br.com.ortiz.domain.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.util.List;

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

    public User findById(Long id) {
        return userDao.find(id);
    }

    public User findByUserName(String username) {
        // TODO MAKES WITH CRITERIA
        List<User> users = userDao.findAll();
        User user = users.stream().filter(u -> username.equals(u.getUsername())).findFirst().get();
        return user;
    }

    public String createToken(User user) throws UnsupportedEncodingException {
        return JWT.create().withClaim("user_id", user.getId().toString()).withClaim("username", user.getUsername()).sign(Algorithm.HMAC256("aloha"));
    }

}
