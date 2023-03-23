package edu.miu.kafkaserver.controller;

import edu.miu.kafkaserver.domain.Computer;
import edu.miu.kafkaserver.domain.CpuData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/cpu-data")
@CrossOrigin
public class CpuDataConsumerController {
    private static final String CPU_TOPIC ="TOPIC_CPU";
    Map<Long, CpuData> lastestCpuData = new HashMap<>();
    Map<Long, Computer> lastestComputers = new HashMap<>();

    @KafkaListener(topics = CPU_TOPIC, groupId = "cpu-data-consumer-group", containerFactory = "cpuDataKafkaListenerContainerFactory")
    public void receiveCpuData(CpuData data) {
        lastestCpuData.put(data.getComputer().getId(), data);
        lastestComputers.put(data.getComputer().getId(), data.getComputer());
        System.out.println("Cpu Data received from Kafka");
    }

    @GetMapping("/{computerID}/get-current-data")
    public CpuData sendData(@PathVariable("computerID") Long computerId) {
        if(computerId == null || lastestCpuData.isEmpty()) return null;
        return lastestCpuData.get(computerId);
    }

    @GetMapping("/get-current-computers")
    public List<Computer> getComputers() {
        return lastestComputers.values().stream().toList();
    }
}
