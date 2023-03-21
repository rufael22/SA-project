package edu.miu.cpudataservice;

import edu.miu.cpudataservice.service.CpuService;
import edu.miu.cpudataservice.service.IMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CpuDataServiceApplication implements CommandLineRunner {
    @Autowired
    CpuService cpuService;
    public static void main(String[] args) {
        SpringApplication.run(CpuDataServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String apiUrl = "http://localhost:19999/api/v1/data?chart=system.cpu";
        apiUrl += "&after=-2&format=json&points=1";
        cpuService.getData(apiUrl);
    }
}
