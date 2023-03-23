package miu.edu.mainnetworkdataservice;

import miu.edu.mainnetworkdataservice.domain.NetworkData;
import miu.edu.mainnetworkdataservice.service.NetworkDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
@EnableDiscoveryClient
public class MainNetworkDataServiceApplication implements CommandLineRunner {
    @Autowired
    NetworkDataService networkDataService;

    public static void main(String[] args) {
        SpringApplication.run(MainNetworkDataServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String apiUrl = "http://localhost:19999/api/v1/data?chart=system.ipv4";
                apiUrl += "&after=-2&format=json&points=1";
                NetworkData data = (NetworkData) networkDataService.getData(apiUrl);
                if(data != null) networkDataService.sendData(data);
            }
        }, 0, 1000);
    }

}
