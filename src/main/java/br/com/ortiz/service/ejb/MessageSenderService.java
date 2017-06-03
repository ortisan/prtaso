package br.com.ortiz.service.ejb;

import br.com.ortiz.domain.entity.Subscription;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by tenta on 03/06/2017.
 */

@Stateless
public class MessageSenderService {

    @Inject
    private SubscriptionService subscriptionService;

    @Schedule(minute = "/1", hour = "")
    public void sendMessage() {
        List<Subscription> subscriptions = subscriptionService.getSubscriptionNotSended();

        Twitter twitter = new TwitterFactory().getInstance();
        for (Subscription subscription : subscriptions) {
            try {
                twitter.sendDirectMessage(subscription.getUser().getTwitterUserId(), subscription.getTopic().getMessage());
                subscription.setSended(true);
            } catch (TwitterException e) {
                // TODO TREAT
                e.printStackTrace();
            }
        }
    }
}
