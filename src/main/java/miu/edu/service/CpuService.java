package miu.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;


@Service
public class CpuService implements IMetricService {
    @Autowired
    RestOperations restOperations;
    @Bean RestOperations restOperations() {
        return new RestTemplate();
    }
    public void getAndSendData(String url) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
    }
}