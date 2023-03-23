package edu.miu.mainramdataservice.service;

import edu.miu.mainramdataservice.domain.Computer;
import edu.miu.mainramdataservice.domain.Metric;
import edu.miu.mainramdataservice.domain.RamData;
import edu.miu.mainramdataservice.repository.RamRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RamDataService implements IMetricService {
    @Value("${computer.id}")
    private Long computerId;

    @Value("${computer.name}")
    private String computerName;
    @Autowired
    RamRepository ramRepository;

    @Autowired
    KafkaProducerService kafkaProducerService;

    @Override
    public Metric getData(String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            JSONParser parser = new JSONParser(response);
            List<Object> data = (List<Object>) parser.parseObject().get("data");
            List<Object> values = (List<Object>) data.get(0);

            Computer computer = new Computer(computerId, computerName);
            RamData ramData = new RamData(computer,
                    Long.parseLong(values.get(0).toString()),
                    Double.parseDouble(values.get(1).toString()),
                    Double.parseDouble(values.get(2).toString()),
                    Double.parseDouble(values.get(3).toString()),
                    Double.parseDouble(values.get(4).toString()),
                    Double.parseDouble(values.get(5).toString()),
                    Double.parseDouble(values.get(6).toString()),
                    Double.parseDouble(values.get(7).toString()),
                    Double.parseDouble(values.get(8).toString()));

            return ramData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void sendData(Metric metric) {
        kafkaProducerService.send((RamData) metric);
        System.out.println("Data sent to Kafka!");


    }

    @Override
    public void save(Metric metric) {
        ramRepository.save((RamData) metric);

    }
}
