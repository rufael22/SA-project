package edu.miu.kafkaserver.controller;

import edu.miu.kafkaserver.domain.NetworkData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/network-data")
@CrossOrigin
public class NetworkDataConsumerController {
    private static final String NETWORK_TOPIC ="TOPIC_NETWORK";
    Map<Long, NetworkData> lastestNetworkData = new HashMap<>();

    @KafkaListener(topics = NETWORK_TOPIC, groupId = "network-data-consumer-group", containerFactory = "networkDataKafkaListenerContainerFactory")
    public void receiveCpuData(NetworkData data) {
        lastestNetworkData.put(data.getComputer().getId(), data);
        System.out.println("Network Data received from Kafka");
    }

    @GetMapping("/{computerID}/get-current-data")
    public NetworkData sendData(@PathVariable("computerID") Long computerId) {
        if(computerId == null || lastestNetworkData.isEmpty()) return null;
        return lastestNetworkData.get(computerId);
    }
}
