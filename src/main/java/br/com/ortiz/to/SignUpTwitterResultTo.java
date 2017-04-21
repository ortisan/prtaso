package br.com.ortiz.to;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by tenta on 23/03/2017.
 */
@XmlRootElement
public class SignUpTwitterResultTo {
    private String redirectUrl;

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

}
