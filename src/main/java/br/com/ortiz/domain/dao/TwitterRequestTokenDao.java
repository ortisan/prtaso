package br.com.ortiz.domain.dao;

import br.com.ortiz.domain.entity.TwitterRequestToken;
import br.com.ortiz.domain.entity.User;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Created by tenta on 21/04/2017.
 */
public class TwitterRequestTokenDao extends GenericDaoJpaImpl<TwitterRequestToken, Long> {

    public Optional<TwitterRequestToken> findByRequestToken(String requestToken) {
        TypedQuery<TwitterRequestToken> query = super.entityManager.createQuery("SELECT t FROM TwitterRequestToken AS t WHERE t.requestToken = :requestToken", TwitterRequestToken.class);
        query.setParameter("requestToken", requestToken);
        List<TwitterRequestToken> tokens = query.getResultList();
        return tokens.stream().findFirst();
    }

}
