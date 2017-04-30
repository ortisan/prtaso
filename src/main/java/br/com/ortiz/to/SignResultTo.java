package br.com.ortiz.to;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by tenta on 13/03/2017.
 */
@XmlRootElement
public class SignResultTo {

    private Long userId;

    private String name;

    private String username;

    private String token;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "SignResultTo{" +
        "userId=" + userId +
        ", name='" + name + '\'' +
        ", username='" + username + '\'' +
        ", token='" + token + '\'' +
        '}';
    }
}
