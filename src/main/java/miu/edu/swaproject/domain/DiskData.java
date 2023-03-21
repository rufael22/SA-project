package miu.edu.swaproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiskData implements Metric {
    private Computer computer;
    private Long time;
    private double in;
    private double out;
}
