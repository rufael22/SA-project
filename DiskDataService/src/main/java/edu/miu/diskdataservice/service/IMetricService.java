package edu.miu.diskdataservice.service;

import edu.miu.cpudataservice.domain.Metric;

public interface IMetricService {
    Metric getData(String url);
    void sendData(Metric metric);

    void save(Metric metric);

}
