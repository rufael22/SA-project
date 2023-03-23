package edu.miu.kafkaserver.controller;

import edu.miu.kafkaserver.domain.DiskData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/disk-data")
@CrossOrigin
public class DiskDataConsumerController {
    private static final String DISK_TOPIC ="TOPIC_DISK";
    Map<Long, DiskData> lastestDiskData = new HashMap<>();

    @KafkaListener(topics = DISK_TOPIC)
    public void receiveCpuData(DiskData data) {
        lastestDiskData.put(data.getComputer().getId(), data);
        System.out.println("Disk Data received from Kafka");
        System.out.println(data.toString());
    }

    @GetMapping("/{computerID}/get-current-data")
    public DiskData sendData(@PathVariable("computerID") Long computerId) {
        if(computerId == null || lastestDiskData.isEmpty()) return null;
        return lastestDiskData.get(computerId);
    }
}
