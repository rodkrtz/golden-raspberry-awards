package br.com.rodkrtz.texo.it.goldenraspberryawards.repository;

import br.com.rodkrtz.texo.it.goldenraspberryawards.repository.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Rodrigo Kreutzfeld
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
