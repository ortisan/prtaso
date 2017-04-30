package br.com.ortiz.domain.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by marcelo on 09/02/17.
 */
@XmlRootElement
@Entity
@Table(name = "\"user\"")
public class User {

    @SequenceGenerator(
    name = "user_sequence_generator",
    sequenceName = "user_seq", allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username")
    private String username;

    // TODO ENCODE PASSWD
    @Column(name = "password")
    private String password;

    @Column(name = "twitter_user_id")
    private String twitterUserId;

    @Column(name = "twitter_token")
    private String twitterToken;

    @Column(name = "twitter_token_secret")
    private String twitterTokenSecret;

    @Column(name = "twitter_verifier")
    private String twitterVerifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTwitterUserId() {
        return twitterUserId;
    }

    public void setTwitterUserId(String twitterUserId) {
        this.twitterUserId = twitterUserId;
    }

    public String getTwitterToken() {
        return twitterToken;
    }

    public void setTwitterToken(String twitterToken) {
        this.twitterToken = twitterToken;
    }

    public String getTwitterTokenSecret() {
        return twitterTokenSecret;
    }

    public void setTwitterTokenSecret(String twitterTokenSecret) {
        this.twitterTokenSecret = twitterTokenSecret;
    }

    public String getTwitterVerifier() {
        return twitterVerifier;
    }

    public void setTwitterVerifier(String twitterVerifier) {
        this.twitterVerifier = twitterVerifier;
    }
}
