package edu.miu.maindiskdataservice.service;




import edu.miu.maindiskdataservice.domain.DiskData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaProducerService {
    private static final String DISK_TOPIC ="TOPIC_DISK";

    @Autowired
    private KafkaTemplate<String, DiskData> kafkaTemplate;

    public void send(DiskData diskData){
        kafkaTemplate.send(DISK_TOPIC, UUID.randomUUID().toString(),diskData);
    }
}
