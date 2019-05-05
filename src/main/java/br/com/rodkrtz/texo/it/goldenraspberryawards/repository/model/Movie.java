package br.com.rodkrtz.texo.it.goldenraspberryawards.repository.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Rodrigo Kreutzfeld
 */
@Data
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer year;

    private boolean winner;
}
