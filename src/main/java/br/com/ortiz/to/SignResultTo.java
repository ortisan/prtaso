package br.com.ortiz.to;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by tenta on 13/03/2017.
 */
@XmlRootElement
public class SignResultTo {
    private Long userId;

    private String token;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        ", token='" + token + '\'' +
        '}';
    }
}
