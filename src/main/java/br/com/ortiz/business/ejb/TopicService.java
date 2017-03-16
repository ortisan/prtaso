package br.com.ortiz.business.ejb;

import br.com.ortiz.domain.dao.GenericDao;
import br.com.ortiz.domain.dao.TopicDao;
import br.com.ortiz.domain.dao.UserDao;
import br.com.ortiz.domain.entity.Topic;
import jdk.nashorn.internal.runtime.options.Option;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Created by marcelo on 09/02/17.
 */
@Stateless
public class TopicService {

    @Inject
    private TopicDao topicDao;

    public Topic save(Topic topic) {
        return topicDao.save(topic);
    }

    public Topic update(Topic topic) {
        return topicDao.update(topic);
    }


    public List<Topic> findAll() {
        // TODO FILTER NON EXPIRED
        return topicDao.findAll();
    }

    public Optional<Topic> findById(Long id) {
        return topicDao.find(id);
    }

}
