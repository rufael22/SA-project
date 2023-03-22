package edu.miu.kafkaserver.config.bindings;

import edu.miu.kafkaserver.domain.CpuData;
import org.apache.kafka.streams.kstream.KStream;

public interface StreamBindings {


    KStream<String, CpuData> inputStream();

    KStream<String, CpuData> mainCpuDataServiceStream();
}
