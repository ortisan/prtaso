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
            sequenceName = "subscription_seq"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_sequence_generator")
    private Long id;
    @OneToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "subscription_user", joinColumns = {@JoinColumn(name = "subscription_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private List<User> users;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
