package edu.miu.diskdataservice.service;


import edu.miu.diskdataservice.domain.Computer;
import edu.miu.diskdataservice.domain.Metric;
import edu.miu.diskdataservice.repository.DiskDataRepository;
import edu.miu.diskdataservice.util.DiskDataFeignClient;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CpuService implements IMetricService {
    @Value("${computer.id}")
    private Long computerId;

    @Value("${computer.name}")
    private String computerName;

    @Autowired
    DiskDataRepository cpuDataRepository;

    @Qualifier("edu.miu.cpudataservice.utils.DiskDataFeignClient")
    @Autowired private DiskDataFeignClient cpuDataFeignClient;

    @Override
    public Metric getData(String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            JSONParser parser = new JSONParser(response);
            List<Object> data = (List<Object>) parser.parseObject().get("data");
            List<Object> values = (List<Object>) data.get(0);

            Computer computer = new Computer(computerId, computerName);
            DickData cpuData = new CpuData(computer,
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
        String response = cpuDataFeignClient.sendRemoteData(metric);
        System.out.println(response);
    }
    @Override
    public void save(Metric metric) {
        cpuDataRepository.save((CpuData) metric);
    }
}

