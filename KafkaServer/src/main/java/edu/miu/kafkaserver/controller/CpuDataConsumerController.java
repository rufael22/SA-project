package edu.miu.kafkaserver.controller;

import edu.miu.kafkaserver.domain.CpuData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/cpu-data")
@CrossOrigin
public class CpuDataConsumerController {
    private static final String CPU_TOPIC ="TOPIC_CPU";
    Map<Long, CpuData> lastestCpuData = new HashMap<>();

    @KafkaListener(topics = CPU_TOPIC)
    public void receiveCpuData(CpuData data) {
        lastestCpuData.put(data.getComputer().getId(), data);
        System.out.println("Cpu Data received from Kafka");
        System.out.println(data.toString());
    }

    @GetMapping("/{computerID}/get-current-data")
    public CpuData sendData(@PathVariable("computerID") Long computerId) {
        if(computerId == null || lastestCpuData.isEmpty()) return null;
        return lastestCpuData.get(computerId);
    }
}
