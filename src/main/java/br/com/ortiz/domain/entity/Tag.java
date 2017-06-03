package br.com.ortiz.domain.entity;

import javax.persistence.*;

/**
 * Created by tenta on 30/04/2017.
 */

@Entity
@Table
public class Tag {

    @SequenceGenerator(
    name = "tag_sequence_generator",
    sequenceName = "tag_seq", allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_sequence_generator")
    private Long id;

    private String tag;


}
