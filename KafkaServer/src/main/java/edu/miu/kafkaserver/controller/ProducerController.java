package edu.miu.kafkaserver.controller;

import edu.miu.kafkaserver.domain.CpuData;
import edu.miu.kafkaserver.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/post")
    public void send(@RequestBody CpuData cpuData){
        kafkaProducerService.send(cpuData);
    }

}
