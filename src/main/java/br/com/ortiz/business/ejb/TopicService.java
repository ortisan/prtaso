package br.com.ortiz.business.ejb;

import br.com.ortiz.domain.dao.GenericDao;
import br.com.ortiz.domain.dao.TopicDao;
import br.com.ortiz.domain.dao.UserDao;
import br.com.ortiz.domain.entity.Topic;

import javax.ejb.Stateless;
import javax.inject.Inject;

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

    public Topic findById(Long id) {
        return topicDao.find(id);
    }

}
