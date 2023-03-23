package edu.miu.kafkaserver.controller;

import edu.miu.kafkaserver.domain.RamData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/ram-data")
@CrossOrigin
public class RamDataConsumerController {
    private static final String RAM_TOPIC ="TOPIC_RAM";
    Map<Long, RamData> lastestNetworkData = new HashMap<>();

    @KafkaListener(topics = RAM_TOPIC, groupId = "ram-data-consumer-group", containerFactory = "ramDataKafkaListenerContainerFactory")
    public void receiveCpuData(RamData data) {
        lastestNetworkData.put(data.getComputer().getId(), data);
        System.out.println("Ram Data received from Kafka");
    }

    @GetMapping("/{computerID}/get-current-data")
    public RamData sendData(@PathVariable("computerID") Long computerId) {
        if(computerId == null || lastestNetworkData.isEmpty()) return null;
        return lastestNetworkData.get(computerId);
    }
}
