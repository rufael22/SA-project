package edu.miu.mainramdataservice.service;





import edu.miu.mainramdataservice.domain.RamData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaProducerService {
    private static final String DISK_TOPIC ="TOPIC_RAM";

    @Autowired
    private KafkaTemplate<String, RamData> kafkaTemplate;

    public void send(RamData ramData){
        kafkaTemplate.send(DISK_TOPIC, UUID.randomUUID().toString(),ramData);
    }
}
