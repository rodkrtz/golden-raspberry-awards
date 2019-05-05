package br.com.rodkrtz.texo.it.goldenraspberryawards.repository.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Rodrigo Kreutzfeld
 */
@Data
@Entity
@Table(name = "producer")
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
