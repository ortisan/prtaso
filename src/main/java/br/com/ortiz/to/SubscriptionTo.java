package br.com.ortiz.to;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by tenta on 15/03/2017.
 */
@XmlRootElement
public class SubscriptionTo {
    private Long subscriptionId;

    private Long userId;

    private Long topicId;

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "SubscriptionTo{" +
        "subscriptionId=" + subscriptionId +
        ", userId=" + userId +
        ", topicId=" + topicId +
        '}';
    }
}
