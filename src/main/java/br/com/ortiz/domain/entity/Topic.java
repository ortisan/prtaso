package br.com.ortiz.domain.entity;

import br.com.ortiz.xml.adapter.LocalDateTimeAdapter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by marcelo on 09/02/17.
 */
@XmlRootElement
@Entity
@Table(name = "topic")
public class Topic {

    @SequenceGenerator(
    name = "topic_sequence_generator",
    sequenceName = "topic_seq", allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_sequence_generator")
    private Long id;

    private String title;

    private String message;

    @Column(name = "creation_date")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime creationDate;

    @Column(name = "send_date")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime sendDate;

    private Boolean sended;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public Boolean getSended() {
        return sended;
    }

    public void setSended(Boolean sended) {
        this.sended = sended;
    }
}
