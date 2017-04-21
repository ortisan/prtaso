package br.com.ortiz.service.ws;

import br.com.ortiz.service.ejb.TopicService;
import br.com.ortiz.domain.entity.Topic;
import br.com.ortiz.security.annotation.Secured;
import br.com.ortiz.service.ws.util.ResponseUtil;

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
@Path("/topics")
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
    public Response findById(@PathParam("id") Long id) {
        Optional<Topic> topicOptional = topicService.findById(id);
        return topicOptional.map((Topic topic) -> ResponseUtil.ok(topic)).orElse(ResponseUtil.notFound());
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
