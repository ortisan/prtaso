package br.com.ortiz.to;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by marcelo on 09/02/17.
 */

@XmlRootElement
public class LoginTo implements Serializable {

    private String username;
    private String password;

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
}
