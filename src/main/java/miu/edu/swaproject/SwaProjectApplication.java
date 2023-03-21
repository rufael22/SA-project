package miu.edu.swaproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SwaProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SwaProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Receiver is running and waiting for messages");

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:19999/api/v1/data?chart=disk_ops.disk0";
        String response = restTemplate.getForObject(apiUrl, String.class);
        System.out.println(response);

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
