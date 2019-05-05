package br.com.rodkrtz.texo.it.goldenraspberryawards.repository;

import br.com.rodkrtz.texo.it.goldenraspberryawards.repository.model.MovieProducer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Rodrigo Kreutzfeld
 */
public interface MovieProducerRepository extends JpaRepository<MovieProducer, Long> {
}
