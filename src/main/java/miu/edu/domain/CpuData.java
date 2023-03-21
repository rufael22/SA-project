package miu.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CpuData implements Metric {
    private Long time;
    private double user;
    private double nice;
    private double system;

}


