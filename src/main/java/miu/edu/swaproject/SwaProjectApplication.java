package miu.edu.swaproject;

import miu.edu.service.CpuService;
import miu.edu.service.IMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SwaProjectApplication implements CommandLineRunner {

//    public SwaProjectApplication(CpuService cpuService) {
//        this.cpuService = cpuService;
//    }
    @Autowired CpuService cpuService;
    @Autowired IMetricService ramService;

    @Bean CpuService cpuService() {
        return new CpuService();
    }
    public static void main(String[] args) {
        SpringApplication.run(SwaProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:19999/api/v1/data?chart=system.cpu";
        apiUrl += "&after=-2&format=json&points=1";
        String response = restTemplate.getForObject(apiUrl, String.class);
        System.out.println(response);

        cpuService.getAndSendData(apiUrl);
    }




    //System.out.println(response);


//    RestTemplate restTemplate = new RestTemplate();
//    restTemplate.getForObject("http://localhost:8080/swaproject/swaproject", String.class);

//    @Bean
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }
//    restTemplate.getForObject("http://localhost:8080/swaproject/swaproject", String.class);
}
