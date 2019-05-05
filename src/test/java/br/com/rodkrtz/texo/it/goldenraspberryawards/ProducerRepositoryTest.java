package br.com.rodkrtz.texo.it.goldenraspberryawards;

import br.com.rodkrtz.texo.it.goldenraspberryawards.repository.ProducerRepository;
import br.com.rodkrtz.texo.it.goldenraspberryawards.repository.model.Producer;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Rodrigo Kreutzfeld
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProducerRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProducerRepository producerRepository;

    @Test
    public void findByNameTest() {

        // given
        Producer p = new Producer();
        p.setName("Test Producer");
        testEntityManager.persistAndFlush(p);

        //when
        Producer found = producerRepository.findByName(p.getName());

        //then
        Assertions.assertThat(found.getName()).isEqualTo(p.getName());

    }

}
