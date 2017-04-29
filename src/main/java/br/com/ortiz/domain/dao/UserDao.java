package br.com.ortiz.domain.dao;

import br.com.ortiz.domain.entity.User;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Created by marcelo on 10/02/17.
 */
public class UserDao extends GenericDaoJpaImpl<User, Long> {

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        TypedQuery<User> query = super.entityManager.createQuery("SELECT u FROM User AS u WHERE u.username = :username and u.password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<User> users = query.getResultList();
        return users.stream().findFirst();
    }

    public Optional<User> findByTwitterToken(String twitterToken) {
        TypedQuery<User> query = super.entityManager.createQuery("SELECT u FROM User AS u WHERE u.twitterToken = :twitterToken", User.class);
        query.setParameter("twitterToken", twitterToken);
        List<User> users = query.getResultList();
        return users.stream().findFirst();
    }

    public Optional<User> findByTwitterUserId(Long twitterUserId) {
        String twitterUserIdStr = "" + twitterUserId;
        TypedQuery<User> query = super.entityManager.createQuery("SELECT u FROM User AS u WHERE u.twitterUserId = :twitterUserId", User.class);
        query.setParameter("twitterUserId", twitterUserIdStr);
        List<User> users = query.getResultList();
        return users.stream().findFirst();
    }

}
