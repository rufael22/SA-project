package edu.miu.diskdataservice.util;


import edu.miu.cpudataservice.repository.CpuDataRepository;
import edu.miu.diskdataservice.domain.DiskData;
import edu.miu.diskdataservice.domain.Metric;
import edu.miu.diskdataservice.repository.DiskDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class DiskDataFeignClientFallback implements DiskDataFeignClient {

    @Autowired private DiskDataRepository cpuDataRepository;

    @Override
    public String sendRemoteData(Metric metric) {
        cpuDataRepository.save((DiskData) metric);
        return "Data saved locally as we cannot reach the parent main service!";
    }
}
