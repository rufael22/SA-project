package edu.miu.kafkaserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
//@Document
public class CpuData implements Metric {

    private Computer computer;
 //   @Id
    private Long time;
    private double user;
    private double nice;
    private double system;

}


