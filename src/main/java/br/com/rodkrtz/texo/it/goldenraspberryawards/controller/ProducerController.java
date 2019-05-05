package br.com.rodkrtz.texo.it.goldenraspberryawards.controller;

import br.com.rodkrtz.texo.it.goldenraspberryawards.repository.ProducerRepository;
import br.com.rodkrtz.texo.it.goldenraspberryawards.repository.model.ProducerStatistics;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * @author Rodrigo Kreutzfeld
 */
@RestController
public class ProducerController {

    @Autowired
    private ProducerRepository producerRepository;

    @GetMapping("/producers/all-winners")
    List<ProducerStatistics> allWinners() {
        return producerRepository.allWinners();
    }

    @GetMapping(value = "/producers/prizes-interval", produces = "application/json; charset=UTF-8")
    String prizesInterval() {

        //group by producerName
        Map<String, List<ProducerStatistics>> mapWinners = allWinners().stream().collect(groupingBy(ProducerStatistics::getProducerName));

        List<JSONObject> listJson = new ArrayList<>();

        for (Map.Entry<String, List<ProducerStatistics>> reg : mapWinners.entrySet()) {

            int interval = 0;
            int followingWin = 0;
            int previousWin = 0;

            Iterator<ProducerStatistics> statistics = reg.getValue().stream().iterator();

            while (statistics.hasNext()) {

                ProducerStatistics p1 = statistics.next();

                if (statistics.hasNext()) {

                    ProducerStatistics p2 = statistics.next();

                    int dif = p2.getMovieYear() - p1.getMovieYear();

                    previousWin = p1.getMovieYear();
                    followingWin = p2.getMovieYear();

                    if (dif > interval) {
                        interval = dif;
                    }
                }
            }

            if (interval > 0) {

                JSONObject o = new JSONObject();
                o.put("producer", reg.getKey());
                o.put("interval", interval);
                o.put("previousWin", previousWin);
                o.put("following", followingWin);

                listJson.add(o);
            }

        }

        IntSummaryStatistics intSummaryStatistics = listJson.stream().mapToInt(o -> o.getInt("interval")).summaryStatistics();

        List<JSONObject> maxList = listJson.stream().filter(o -> o.getInt("interval") == intSummaryStatistics.getMax()).collect(toList());

        List<JSONObject> minList = listJson.stream().filter(o -> o.getInt("interval") == intSummaryStatistics.getMin()).collect(toList());

        JSONObject o = new JSONObject();
        JSONArray min = new JSONArray();
        JSONArray max = new JSONArray();
        minList.forEach(min::put);
        maxList.forEach(max::put);
        o.put("min", min);
        o.put("max", max);

        return o.toString(1);
    }
}
