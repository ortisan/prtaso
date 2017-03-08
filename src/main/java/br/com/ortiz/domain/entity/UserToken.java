package br.com.ortiz.domain.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by tenta on 07/03/2017.
 */
@XmlRootElement
public class UserToken extends User {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
