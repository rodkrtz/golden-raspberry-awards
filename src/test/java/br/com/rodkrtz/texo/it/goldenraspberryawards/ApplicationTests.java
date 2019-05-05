package br.com.rodkrtz.texo.it.goldenraspberryawards;

import br.com.rodkrtz.texo.it.goldenraspberryawards.controller.ProducerController;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Rodrigo Kreutzfeld
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private ProducerController producerController;


    @Test
    public void contextLoads() {
        Assertions.assertThat(producerController).isNotNull();
    }

}
