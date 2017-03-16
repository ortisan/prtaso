package br.com.ortiz.service.ws;

import br.com.ortiz.annotations.Secured;
import br.com.ortiz.business.ejb.SubscriptionService;
import br.com.ortiz.business.ejb.TopicService;
import br.com.ortiz.domain.entity.Subscription;
import br.com.ortiz.domain.entity.Topic;
import br.com.ortiz.service.ws.util.ResponseUtil;
import br.com.ortiz.to.SubscriptionTo;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/**
 * Created by marcelo on 09/02/17.
 */
@Secured
@Path("/subscriptions")
public class SubscriptionWs {

    @Inject
    private SubscriptionService subscriptionService;

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public SubscriptionTo save(SubscriptionTo subscriptionTo) {
        Subscription subscription = subscriptionService.subscribe(subscriptionTo);
        subscriptionTo.setSubscriptionId(subscription.getId());
        return subscriptionTo;
    }

}
