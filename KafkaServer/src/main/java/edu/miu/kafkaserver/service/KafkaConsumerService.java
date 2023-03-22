package edu.miu.kafkaserver.service;


import edu.miu.kafkaserver.domain.CpuData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaConsumerService {
    private static final String CPU_TOPIC ="TOPIC_CPU";
    private static final String DISK_TOPIC ="TOPIC_DISK";
    private static final String NET_TOPIC ="TOPIC_NETWORK";
    private static final String RAM_TOPIC ="TOPIC_RAM";


    @KafkaListener(topics = CPU_TOPIC)
    public void receiveCpuData(CpuData data) {
        System.out.println("Data received from Kafka");
        System.out.println(data.toString());
    }
}
