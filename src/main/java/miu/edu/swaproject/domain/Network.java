package miu.edu.swaproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Network implements Metric {

    private Computer computer;
    private Long time;
    private double received;
    private double sent;
}

