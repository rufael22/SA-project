package edu.miu.cpudataservice.service;

import edu.miu.cpudataservice.domain.CpuData;
import edu.miu.cpudataservice.domain.Metric;
import edu.miu.cpudataservice.repository.CpuDataRepository;
import feign.Feign;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CpuService implements IMetricService {

    @Autowired
    CpuDataRepository cpuDataRepository;

    @Override
    public Metric getData(String url) {
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

            return cpuData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void sendData(String url, Metric metric) {
        RestTemplate restTemplate = new RestTemplate();
        String url2 = "http://localhost:19999/api/v1/data?chart=system.cpu";
        restTemplate.postForObject(url, metric, Metric.class);

    }

    @FeignClient(name = "MainCpuDataService", url = "http://", fallback = CpuDataServiceClientFallback.class)
    interface CpuDataServiceClient {
        @PostMapping("/api/v1/data")
        void sendRemoteData(@RequestBody Metric metric);
    }
    class CpuDataServiceClientFallback implements CpuDataServiceClient {
        @Override
        public void sendRemoteData(Metric metric) {
            cpuDataRepository.save((CpuData) metric);
        }
    }
    @Override
    public void save(Metric metric) {
        cpuDataRepository.save((CpuData) metric);

    }


}

