package miu.edu.mainnetworkdataservice.service;





import miu.edu.mainnetworkdataservice.domain.NetworkData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaProducerService {
    private static final String DISK_TOPIC ="TOPIC_NETWORK";

    @Autowired
    private KafkaTemplate<String, NetworkData> kafkaTemplate;

    public void send(NetworkData networkData){
        kafkaTemplate.send(DISK_TOPIC, UUID.randomUUID().toString(),networkData);
    }
}
