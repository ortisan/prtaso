package br.com.ortiz.domain.entity;

import javax.persistence.*;

/**
 * Created by tenta on 21/04/2017.
 */
@Entity
@Table(name = "twitter_request_token")
public class TwitterRequestToken {
    @SequenceGenerator(
    name = "twitter_request_token_sequence_generator",
    sequenceName = "twitter_request_token_seq"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "twitter_request_token_sequence_generator")
    private Long id;

    @Column(name = "request_token")
    private String requestToken;

    @Column(name = "token_secret")
    private String tokenSecret;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }
}
