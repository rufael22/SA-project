package miu.edu.swaproject.service;

import org.springframework.stereotype.Service;

@Service
public interface IMetricService {
    public  void getAndSendData(String url);

}
