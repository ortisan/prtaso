package br.com.ortiz.service.ws;

import br.com.ortiz.annotations.Secured;
import br.com.ortiz.business.ejb.TopicService;
import br.com.ortiz.domain.entity.Topic;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by marcelo on 09/02/17.
 */
@Secured
@Path("/topic")
public class TopicWs {

    @Inject
    private TopicService topicService;


    @Path("/")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Topic> findAll() {
        return topicService.findAll();
    }


    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Topic findById(@PathParam("id") Long id) {
        return topicService.findById(id);
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Topic save(Topic topic) {
        topicService.save(topic);
        return topic;
    }

    @Path("/")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Topic update(Topic topic) {
        topicService.update(topic);
        return topic;
    }

}
