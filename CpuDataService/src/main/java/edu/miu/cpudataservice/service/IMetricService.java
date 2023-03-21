package edu.miu.cpudataservice.service;

import edu.miu.cpudataservice.domain.Metric;
import org.springframework.stereotype.Service;

@Service
public interface IMetricService {
    void getAndSendData(String url);
    Metric getData(String url);
    void sendData(String url, Metric metric);

    void save(Metric metric);

}
