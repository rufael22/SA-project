package edu.miu.kafkaserver.service;


import edu.miu.kafkaserver.domain.CpuData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaProducerService {
    private static final String CPU_TOPIC ="TOPIC_CPU";

    @Autowired
    private KafkaTemplate<String, CpuData> kafkaTemplate;

    public void send(CpuData cpuData){
        kafkaTemplate.send(CPU_TOPIC, UUID.randomUUID().toString(),cpuData);
    }
}
