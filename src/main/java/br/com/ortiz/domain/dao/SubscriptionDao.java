package br.com.ortiz.domain.dao;

import br.com.ortiz.domain.entity.Subscription;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by marcelo on 15/03/17.
 */
public class SubscriptionDao extends GenericDaoJpaImpl<Subscription, Long> {

    // Subscription between 15 minutes of now
    private static final Integer DEFAULT_DELTA_TIME_MINUTES = 15;

    public List<Subscription> getSubscriptionNotSended() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowInit = now.minusMinutes(-DEFAULT_DELTA_TIME_MINUTES);
        LocalDateTime nowFinish = now.plusMinutes(DEFAULT_DELTA_TIME_MINUTES);
        TypedQuery<Subscription> query = super.entityManager.createQuery("SELECT t FROM Subscription AS s JOIN s.topic AS t WHERE t.sended = false AND t.sendDate BETWEEN :init AND :finish", Subscription.class);
        query.setParameter("init", nowInit);
        query.setParameter("finish", nowFinish);
        List<Subscription> subscriptions = query.getResultList();
        return subscriptions;
    }
}
