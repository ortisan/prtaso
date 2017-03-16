package br.com.ortiz.business.ejb;

import br.com.ortiz.domain.dao.SubscriptionDao;
import br.com.ortiz.domain.entity.Subscription;
import br.com.ortiz.domain.entity.Topic;
import br.com.ortiz.domain.entity.User;
import br.com.ortiz.to.SubscriptionTo;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by tenta on 15/03/2017.
 */
@Stateless
public class SubscriptionService {

    @Inject
    private UserService userService;

    @Inject
    private TopicService topicService;

    @Inject
    private SubscriptionDao subscriptionDao;

    public Subscription subscribe(SubscriptionTo subscriptionTo) {
        User user = userService.findById(subscriptionTo.getUserId()).get();
        Topic topic = topicService.findById(subscriptionTo.getTopicId()).get();
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setTopic(topic);
        return subscriptionDao.save(subscription);
    }
}
