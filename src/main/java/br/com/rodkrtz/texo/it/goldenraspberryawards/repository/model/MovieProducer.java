package br.com.rodkrtz.texo.it.goldenraspberryawards.repository.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Rodrigo Kreutzfeld
 */
@Data
@Entity
@Table(name = "movie_producer")
public class MovieProducer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_movie")
    private Long idMovie;

    @Column(name = "id_producer")
    private Long idProducer;
}
