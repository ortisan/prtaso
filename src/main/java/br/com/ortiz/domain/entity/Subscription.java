package br.com.ortiz.domain.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by marcelo on 11/02/17.
 */
@Entity
@Table
public class Subscription {
    @SequenceGenerator(
    name = "subscription_sequence_generator",
    sequenceName = "subscription_seq", allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_sequence_generator")
    private Long id;
    @OneToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
