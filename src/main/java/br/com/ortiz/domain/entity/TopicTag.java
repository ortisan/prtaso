package br.com.ortiz.domain.entity;

import javax.persistence.*;

/**
 * Created by marcelo on 11/02/17.
 */
//@Entity
//@Table(name = "topic_tag")
public class TopicTag {
    @SequenceGenerator(
    name = "topic_tag_sequence_generator",
    sequenceName = "topic_tag_seq", allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_tag_sequence_generator")
    private Long id;
    @OneToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;

    @OneToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    private Tag tag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
