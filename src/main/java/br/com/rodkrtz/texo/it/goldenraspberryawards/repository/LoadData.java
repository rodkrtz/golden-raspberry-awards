package br.com.rodkrtz.texo.it.goldenraspberryawards.repository;

import br.com.rodkrtz.texo.it.goldenraspberryawards.repository.model.Movie;
import br.com.rodkrtz.texo.it.goldenraspberryawards.repository.model.MovieProducer;
import br.com.rodkrtz.texo.it.goldenraspberryawards.repository.model.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Rodrigo Kreutzfeld
 */
@Configuration
@Slf4j
public class LoadData {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    MovieProducerRepository movieProducerRepository;

    @Bean
    CommandLineRunner initLoad() {

        return done -> {
            InputStream is = new ClassPathResource("movielist.csv").getInputStream();

            CSVParser csvParser = new CSVParser(new InputStreamReader(is), CSVFormat.newFormat(';')
                    .withFirstRecordAsHeader());

            csvParser.getRecords().forEach(i -> {

                Integer year = Integer.valueOf(i.get(0));
                String title = i.get(1).trim();
                String[] producers = i.get(3).split(",| and ");
                boolean winner = i.get(4).trim().equalsIgnoreCase("yes");


                Movie movie = new Movie();
                movie.setTitle(title);
                movie.setYear(year);
                movie.setWinner(winner);
                movieRepository.save(movie);

                for (String name : producers) {
                    String trimmedName = name.trim();
                    Producer p = producerRepository.findByName(trimmedName);
                    if (p == null) {
                        p = new Producer();
                        p.setName(trimmedName);
                        producerRepository.save(p);
                    }
                    MovieProducer mp = new MovieProducer();
                    mp.setIdMovie(movie.getId());
                    mp.setIdProducer(p.getId());
                    movieProducerRepository.save(mp);
                }

            });

        };
    }

}
