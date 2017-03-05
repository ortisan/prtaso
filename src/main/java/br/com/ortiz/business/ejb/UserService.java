package br.com.ortiz.business.ejb;

import br.com.ortiz.domain.dao.UserDao;
import br.com.ortiz.domain.entity.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
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

}
