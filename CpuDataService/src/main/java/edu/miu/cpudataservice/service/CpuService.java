package edu.miu.cpudataservice.service;

import edu.miu.cpudataservice.domain.CpuData;
import edu.miu.cpudataservice.repository.CpuDataRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CpuService implements IMetricService {

    @Autowired
    CpuDataRepository cpuDataRepository;
    public void getAndSendData(String url) {
        while (true) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                String response = restTemplate.getForObject(url, String.class);

                JSONParser parser = new JSONParser(response);
                List<Object> data = (List<Object>) parser.parseObject().get("data");
                List<Object> values = (List<Object>) data.get(0);

                CpuData cpuData = new CpuData(null,
                        Long.parseLong(values.get(0).toString()),
                        Double.parseDouble(values.get(1).toString()),
                        Double.parseDouble(values.get(2).toString()),
                        Double.parseDouble(values.get(3).toString()));

                restTemplate.postForEntity("http://10.200.2.154:8080/cpu/send", cpuData, String.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}