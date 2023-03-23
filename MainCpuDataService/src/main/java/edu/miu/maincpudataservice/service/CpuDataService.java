package edu.miu.maincpudataservice.service;

import edu.miu.maincpudataservice.domain.Computer;
import edu.miu.maincpudataservice.domain.CpuData;
import edu.miu.maincpudataservice.domain.Metric;
import edu.miu.maincpudataservice.repository.CpuRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class CpuDataService implements IMetricService{
    @Value("${computer.id}")
    private Long computerId;

    @Value("${computer.name}")
    private String computerName;

    @Autowired
    CpuRepository cpuRepository;

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
            CpuData cpuData = new CpuData(computer,
                    Long.parseLong(values.get(0).toString()),
                    Double.parseDouble(values.get(1).toString()),
                    Double.parseDouble(values.get(2).toString()),
                    Double.parseDouble(values.get(3).toString()));

            return cpuData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void sendData(Metric metric) {
        kafkaProducerService.send((CpuData)metric);
        System.out.println("Data sent to Kafka!");
    }

    @Override
    public void save(Metric metric) {
        cpuRepository.save((CpuData) metric);

    }
}
