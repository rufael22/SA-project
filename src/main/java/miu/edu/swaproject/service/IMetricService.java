package miu.edu.swaproject.service;

import org.springframework.stereotype.Service;

@Service
public interface IMetricService {
    public  void getAndSendData(String url);
    public void getData(String url);
    public void sendData(String url);

    public void saveData(String url);



}
