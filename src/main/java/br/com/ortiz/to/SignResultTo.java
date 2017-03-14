package br.com.ortiz.to;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by tenta on 13/03/2017.
 */
@XmlRootElement
public class SignResultTo {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "SignResultTo{" +
        "token='" + token + '\'' +
        '}';
    }
}
